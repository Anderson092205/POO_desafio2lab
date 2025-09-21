package com.editorial.editorialweb.dao;

import com.editorial.editorialweb.beans.Autor;
import com.editorial.editorialweb.dao.Conexion;

import java.sql.*;
import java.util.*;

public class AutorDAO {
    public List<Autor> listar() {
        List<Autor> lista = new ArrayList<>();
        try (Connection con = Conexion.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM autor")) {
            while (rs.next()) {
                lista.add(new Autor(rs.getInt("id"), rs.getString("nombre"), rs.getString("nacionalidad")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Autor obtenerPorId(int id) {
        Autor autor = null;
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM autor WHERE id = ?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                autor = new Autor(rs.getInt("id"), rs.getString("nombre"), rs.getString("nacionalidad"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return autor;
    }

    public void insertar(Autor autor) {
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement("INSERT INTO autor (nombre, nacionalidad) VALUES (?, ?)")) {
            ps.setString(1, autor.getNombre());
            ps.setString(2, autor.getNacionalidad());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actualizar(Autor autor) {
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement("UPDATE autor SET nombre = ?, nacionalidad = ? WHERE id = ?")) {
            ps.setString(1, autor.getNombre());
            ps.setString(2, autor.getNacionalidad());
            ps.setInt(3, autor.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminar(int id) {
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement("DELETE FROM autor WHERE id = ?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}