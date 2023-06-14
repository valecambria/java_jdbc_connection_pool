package org.example;

import org.example.models.Producto;
import org.example.repositorio.ProductoRepositorioImpl;
import org.example.repositorio.Repositorio;
import org.example.util.ConexionBaseDatos;

import java.sql.Connection;
import java.sql.SQLException;

public class EjemploJDBCDelete {
    public static void main(String[] args) {
            Repositorio<Producto> repositorio = new ProductoRepositorioImpl();
            System.out.println("======= listar =======");
            repositorio.listar().forEach(System.out::println);

            System.out.println("======= obtener por id =======");
            System.out.println(repositorio.porId(2L));

            System.out.println("======= insertar nuevo producto =======");
            repositorio.eliminar(6L);
            repositorio.listar().forEach(System.out::println);

    }
}