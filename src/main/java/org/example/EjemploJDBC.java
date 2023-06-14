package org.example;

import org.example.models.Categoria;
import org.example.models.Producto;
import org.example.repositorio.ProductoRepositorioImpl;
import org.example.repositorio.Repositorio;
import java.util.Date;

public class EjemploJDBC {
    public static void main(String[] args) {

            Repositorio<Producto> repositorio = new ProductoRepositorioImpl();
            System.out.println("======= listar =======");
            repositorio.listar().forEach(System.out::println);

            System.out.println("======= obtener por id =======");
            System.out.println(repositorio.porId(2L));

            System.out.println("======= insertar nuevo producto =======");
            Producto producto = new Producto();
            producto.setNombre("Teclado mecanico");
            producto.setPrecio(500);
            producto.setFechaRegistro(new Date());
            Categoria categoria = new Categoria();
            categoria.setId(3L);
            producto.setCategoria(categoria);
            repositorio.guardar(producto);
            System.out.println("Producto guardado con exito");
            repositorio.listar().forEach(System.out::println);

    }
}