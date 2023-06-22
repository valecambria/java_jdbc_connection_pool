package org.example.repositorio;

import org.example.models.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaRepositorioImpl implements Repositorio<Categoria> {
    private Connection conn;

    public CategoriaRepositorioImpl(Connection conn) {
        this.conn = conn;
    }

    public CategoriaRepositorioImpl() {
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Categoria> listar() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();
        try(Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM categorias")){
            while(rs.next()){
                categorias.add(crearCategoria(rs));
            }
        }
        return categorias;
    }

    @Override
    public Categoria porId(Long id) throws SQLException {
        Categoria categoria = new Categoria();
        try(PreparedStatement stmt = conn.prepareStatement("SELECT * FROM categorias as c WHERE c.id=?")){
            stmt.setLong(1, id); //me posiciono en el indice 1 ya que es el unico parametro que tenemos, y le paso el valor (el id)
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){ //nos posicionamos en el primer registro si es que encuentra la categoria por el id
                    categoria = crearCategoria(rs); //asigno la categoria con el metodo de crear categoria
                }
            }
        }
        return categoria;
    }

    @Override
    public Categoria guardar(Categoria categoria) throws SQLException {
        String sql = null;
        if (categoria.getId() != null && categoria.getId() > 0){
            sql = "UPDATE categorias SET nombre=? WHERE id=?";
        }else{
            sql = "INSERT INTO categorias(nombre) VALUES(?)";
        }
        try(PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){ //necesitamos que devuelva el id generado del insert, entonces pasamos el statment
            //con eso habilitamos para que despues de ejecutar, con el execute update, generar un cursor (resultset), con el metodo get generated keys
            stmt.setString(1, categoria.getNombre());
            if (categoria.getId() != null && categoria.getId() > 0){
                stmt.setLong(2, categoria.getId());
            }
            stmt.executeUpdate();

            if(categoria.getId() == null){
                try(ResultSet rs = stmt.getGeneratedKeys()){
                    if (rs.next()){
                        categoria.setId(rs.getLong(1));
                    }
                }
            }
        }
        return categoria;
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        try(PreparedStatement stmt = conn.prepareStatement("DELETE FROM categorias WHERE id=?")){
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    private static Categoria crearCategoria(ResultSet rs) throws SQLException {
        Categoria categoria = new Categoria();
        categoria.setId(rs.getLong("id"));
        categoria.setNombre(rs.getString("nombre"));
        return categoria;
    }
}
