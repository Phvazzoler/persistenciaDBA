package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {
    public void adicionar (Contato contato){
        String sql = "INSERT INTO contatos(nome, telefone) VALUES(?,?)"  ;
        try (Connection conn = DataBase.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, contato.getNome());
            pstmt.setString(2, contato.getTelefone());
            pstmt.executeUpdate();
        }catch (SQLException e ){
            System.out.println("Erro ao adicionar contato : " + e.getMessage());



        }
    }
    public List<Contato> listar(){
        List<Contato> contatos = new ArrayList<>();
        String sql = "SELECT * FROM contatos";
        try (Connection conn = DataBase.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String telefone = rs.getString("telefone");
                contatos.add(new Contato(id, nome, telefone));
            }
        }catch (SQLException e ){
            System.out.println("Erro ao listar contatos : " + e.getMessage());

        }
        return contatos ;
    }
    public void atualizar (Contato contato){
        String sql = "UPDATE contatos SET nome = ?, telefone = ? , WHERE id = ?";
        try (Connection conn = DataBase.conectar();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, contato.getNome());
            pstmt.setString(2, contato.getTelefone());
            pstmt.setInt(3, contato.getId());
            pstmt.execute();
        }catch (SQLException e ){
            System.out.println("Erro ao atualizar contato " + e.getMessage());
        }
        }
    public void remover(int id ){
        String sql = "DELETE FROM contatos WHERE id = ? ";
        try(Connection conn = DataBase.conectar();
            PreparedStatement psmt = conn.prepareStatement(sql)) {
            psmt.setInt(1, id);
            psmt.executeUpdate();
        }catch (SQLException e ){
            System.out.println("Erro ao remover contato : " + e.getMessage());
        }
    }
}
