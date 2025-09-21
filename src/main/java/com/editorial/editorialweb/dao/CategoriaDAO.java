package com.editorial.editorialweb.dao;

import com.editorial.editorialweb.beans.Categoria;
import java.sql.*;
import java.util.*;

public class CategoriaDAO {
    public List<Categoria> listar() {
        List<Categoria> lista = new ArrayList<>();
        try (Connection con = Conexion.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM categoria")) {
            while (rs.next()) {
                lista.add(new Categoria(rs.getInt("id"), rs.getString("nombre")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Categoria obtenerPorId(int id) {
        Categoria categoria = null;
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM categoria WHERE id = ?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                categoria = new Categoria(rs.getInt("id"), rs.getString("nombre"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categoria;
    }

    public void insertar(Categoria categoria) {
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement("INSERT INTO categoria (nombre) VALUES (?)")) {
            ps.setString(1, categoria.getNombre());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actualizar(Categoria categoria) {
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement("UPDATE categoria SET nombre = ? WHERE id = ?")) {
            ps.setString(1, categoria.getNombre());
            ps.setInt(2, categoria.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminar(int id) {
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement("DELETE FROM categoria WHERE id = ?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}