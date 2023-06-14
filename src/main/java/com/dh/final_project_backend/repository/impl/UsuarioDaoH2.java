package com.dh.final_project_backend.repository.impl;

import com.dh.final_project_backend.entity.Domicilio;
import com.dh.final_project_backend.entity.Usuario;
import com.dh.final_project_backend.repository.IDao;
import com.dh.final_project_backend.repository.configuration.ConnectionJDBC;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UsuarioDaoH2 implements IDao<Usuario> {

    private final static Logger logger = LogManager.getLogger(DomicilioDaoH2.class);
    @Autowired
    private ConnectionJDBC connectionJDBC;

    public UsuarioDaoH2(ConnectionJDBC connectionJDBC) {
        this.connectionJDBC = connectionJDBC;
    }

    @Override
    public Usuario guardar(Usuario usuario) {
        logger.info("Guardando usuario: " + usuario.toString());
        Connection connection = null;
        PreparedStatement ps = null;

        try{
            connection = connectionJDBC.connectToDB();

            String insert_sql = "INSERT INTO USUARIOS (USUARIO, PASSWORD, ROL) VALUES(?,?,?)";

            connection.setAutoCommit(false);

            ps = connection.prepareStatement(insert_sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.getPassword());
            ps.setString(3, usuario.getRol());

            ps.executeUpdate();

            ResultSet ids = ps.getGeneratedKeys();
            if(ids.next()){
                usuario.setId(ids.getInt(1));
            }

            connection.commit();
            connection.setAutoCommit(true);

            ps.close();

        } catch (SQLException e){
            logger.info("Se produjo una excepción: SQLException");
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {

                logger.info("Se produjo una excepción: SQLException");
                e.printStackTrace();
            }
        } finally {
            try{
                assert connection != null;
                connection.close();
            } catch (SQLException e) {
                logger.info("No se pudo cerrar la conexión con la DB");
                e.printStackTrace();
            }
        }
        logger.info("Usuario guardado: " + usuario.toString());
        System.out.println(usuario);
        return usuario;
    }

    @Override
    public Usuario buscar(Integer id) {
        logger.info("Buscando usuario con id: " + id);
        Connection connection = null;
        PreparedStatement ps = null;
        Usuario usuario = null;

        try{
            connection = connectionJDBC.connectToDB();

            String select_sql = "SELECT * FROM USUARIOS WHERE ID=?";

            connection.setAutoCommit(false);

            ps = connection.prepareStatement(select_sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Integer idUsuario = rs.getInt(1);
                String usuarioUsuario = rs.getString(2);
                String passwordUsuario = rs.getString(3);
                String rolUsuario = rs.getString(4);

                usuario = new Usuario(idUsuario, usuarioUsuario, passwordUsuario, rolUsuario);
            }

            connection.commit();
            connection.setAutoCommit(true);

            ps.close();

        } catch (SQLException e){
            logger.info("Se produjo una excepción: SQLException");
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                logger.info("Se produjo una excepción: SQLException");
                e.printStackTrace();
            }
        } finally {
            try{
                assert connection != null;
                connection.close();
            } catch (SQLException e) {
                logger.info("No se pudo cerrar la conexión con la DB");
                e.printStackTrace();
            }
        }
        logger.info("Usuario encontrado: " + usuario.toString());
        System.out.println(usuario);
        return usuario;
    }

    @Override
    public Usuario actualizar(Usuario usuario) {
        logger.info("Actualizando usuario: " + usuario.toString());
        Connection connection = null;
        PreparedStatement ps = null;

        try{
            connection = connectionJDBC.connectToDB();

            String update_sql = "UPDATE USUARIOS SET USUARIO=?, PASSWORD=?, ROL=? WHERE ID=?";

            connection.setAutoCommit(false);

            ps = connection.prepareStatement(update_sql);
            ps.setString(1,usuario.getUsuario());
            ps.setString(2,usuario.getPassword());
            ps.setString(3, usuario.getRol());
            ps.setInt(4, usuario.getId());

            ps.executeUpdate();

            connection.commit();
            connection.setAutoCommit(true);

            ps.close();

        } catch (SQLException e){
            logger.info("Se produjo una excepción: SQLException");
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                logger.info("Se produjo una excepción: SQLException");
                e.printStackTrace();
            }
        } finally {
            try{
                assert connection != null;
                connection.close();
            } catch (SQLException e) {
                logger.info("No se pudo cerrar la conexión con la DB");
                e.printStackTrace();
            }
        }
        logger.info("Usuario actualizado: " + usuario.toString());
        System.out.println(usuario);
        return usuario;
    }

    @Override
    public void eliminar(Integer id) {
        logger.info("Eliminando usuario con id: " + id);
        Connection connection = null;
        PreparedStatement ps = null;

        try{
            connection = connectionJDBC.connectToDB();

            String delete_sql = "DELETE FROM USUARIOS WHERE ID=?";

            connection.setAutoCommit(false);

            ps = connection.prepareStatement(delete_sql);
            ps.setInt(1, id);

            ps.executeUpdate();

            connection.commit();
            connection.setAutoCommit(true);

            ps.close();

        } catch (SQLException e){
            logger.info("Se produjo una excepción: SQLException");
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                logger.info("Se produjo una excepción: SQLException");
                e.printStackTrace();
            }
        } finally {
            try{
                assert connection != null;
                connection.close();
            } catch (SQLException e) {
                logger.info("No se pudo cerrar la conexión con la DB");
                e.printStackTrace();
            }
        }
        logger.info("Usuario eliminado con id: " + id);
        System.out.println("Usuario eliminado con id: " + id);
    }

    @Override
    public List<Usuario> buscarTodos() {
        logger.info("Buscando usuarios...");
        Connection connection = null;
        PreparedStatement ps = null;
        Usuario usuario = null;
        List<Usuario> usuarios = new ArrayList<>();

        try{
            connection = connectionJDBC.connectToDB();

            String select_all_sql = "SELECT * FROM USUARIOS";

            connection.setAutoCommit(false);

            ps = connection.prepareStatement(select_all_sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Integer idUsuario = rs.getInt(1);
                String usuarioUsuario = rs.getString(2);
                String passwordUsuario = rs.getString(3);
                String rolUsuario = rs.getString(4);

                usuario = new Usuario(idUsuario, usuarioUsuario, passwordUsuario, rolUsuario);

                usuarios.add(usuario);

            }

            connection.commit();
            connection.setAutoCommit(true);

            ps.close();

        } catch (SQLException e){
            logger.info("Se produjo una excepción: SQLException");
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                logger.info("Se produjo una excepción: SQLException");
                e.printStackTrace();
            }
        } finally {
            try{
                assert connection != null;
                connection.close();
            } catch (SQLException e) {
                logger.info("No se pudo cerrar la conexión con la DB");
                e.printStackTrace();
            }
        }
        logger.info("Usuarios encontrados...");
        System.out.println(usuarios);
        return usuarios;
    }
}
