package org.example;

import org.example.models.Producto;
import org.example.repositorio.ProductoRepositorioImpl;
import org.example.repositorio.Repositorio;
import org.example.util.ConexionBaseDatos;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class EjemploJDBCActualizar {
    public static void main(String[] args) {

        try (Connection conn = ConexionBaseDatos.getConnection();){ //se encarga de administrar las conexiones y drivers que tenemos disponibles
            Repositorio<Producto> repositorio = new ProductoRepositorioImpl();
            System.out.println("======= listar =======");
            repositorio.listar().forEach(System.out::println);

            System.out.println("======= obtener por id =======");
            System.out.println(repositorio.porId(2L));

            System.out.println("======= insertar nuevo producto =======");
            Producto producto = new Producto();
            producto.setId(3L);
            producto.setNombre("Teclado mecanico");
            producto.setPrecio(500);
            repositorio.guardar(producto);
            System.out.println("Producto guardado con exito");
            repositorio.listar().forEach(System.out::println);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}