package com.editorial.editorialweb.dao;

import com.editorial.editorialweb.beans.*;
import java.sql.*;
import java.util.*;

public class MaterialDAO {
    public List<Material> listar() {
        List<Material> lista = new ArrayList<>();
        try (Connection con = Conexion.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT m.id, m.titulo, m.tipo, c.id AS cat_id, c.nombre AS cat_nombre, a.id AS aut_id, a.nombre AS aut_nombre, a.nacionalidad FROM material m JOIN categoria c ON m.id_categoria = c.id JOIN autor a ON m.id_autor = a.id")) {
            while (rs.next()) {
                Categoria categoria = new Categoria(rs.getInt("cat_id"), rs.getString("cat_nombre"));
                Autor autor = new Autor(rs.getInt("aut_id"), rs.getString("aut_nombre"), rs.getString("nacionalidad"));
                Material material = new Material(rs.getInt("id"), rs.getString("titulo"), rs.getString("tipo"), categoria, autor);
                lista.add(material);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Material obtenerPorId(int id) {
        Material material = null;
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM material WHERE id = ?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Categoria categoria = new CategoriaDAO().obtenerPorId(rs.getInt("id_categoria"));
                Autor autor = new AutorDAO().obtenerPorId(rs.getInt("id_autor"));
                material = new Material(rs.getInt("id"), rs.getString("titulo"), rs.getString("tipo"), categoria, autor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return material;
    }

    public void insertar(Material m) {
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement("INSERT INTO material (titulo, tipo, id_categoria, id_autor) VALUES (?, ?, ?, ?)")) {
            ps.setString(1, m.getTitulo());
            ps.setString(2, m.getTipo());
            ps.setInt(3, m.getCategoria().getId());
            ps.setInt(4, m.getAutor().getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actualizar(Material m) {
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement("UPDATE material SET titulo = ?, tipo = ?, id_categoria = ?, id_autor = ? WHERE id = ?")) {
            ps.setString(1, m.getTitulo());
            ps.setString(2, m.getTipo());
            ps.setInt(3, m.getCategoria().getId());
            ps.setInt(4, m.getAutor().getId());
            ps.setInt(5, m.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminar(int id) {
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement("DELETE FROM material WHERE id = ?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}