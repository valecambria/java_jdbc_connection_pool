package org.example;

import org.example.models.Categoria;
import org.example.models.Producto;
import org.example.repositorio.CategoriaRepositorioImpl;
import org.example.repositorio.ProductoRepositorioImpl;
import org.example.repositorio.Repositorio;
import org.example.servicio.CatalogoServicio;
import org.example.servicio.Servicio;
import org.example.util.ConexionBaseDatos;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class EjemploJDBC {
    public static void main(String[] args) throws SQLException {

        Servicio servicio = new CatalogoServicio();

        System.out.println("======= listar =======");
        servicio.listar().forEach(System.out::println);

        System.out.println("======= Insertar nueva categoria =======");
        Categoria categoria = new Categoria();
        categoria.setNombre("Iluminacion");

        System.out.println("======= insertar nuevo producto =======");
        Producto producto = new Producto();
        producto.setNombre("Lampara led");
        producto.setPrecio(890);
        producto.setFechaRegistro(new Date());
        producto.setSku("el12234");
        servicio.guardarProductoConCategoria(producto, categoria);
        System.out.println("Producto guardado con exito" + producto.getId());
        servicio.listar().forEach(System.out::println);

    }
}