/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infoX.telas;

/**
 *
 * @author Eduardo Azevedo
 */
import java.sql.*;
import com.infoX.dal.ModuloConexao;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

public class TelaCliente extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    private Object txtUsu;

    public TelaCliente() {
        initComponents();
        conexao = ModuloConexao.conector();
    }

    // Adiciona os Clientes
    private void adicionar() {
        String sql = "insert into tbclientes(nomecli,endcli,fonecli, emailcli)values(?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtCliNome.getText());
            pst.setString(2, txtCliEndereco.getText());
            pst.setString(3, txtCliTelefone.getText());
            pst.setString(4, txtCliEmail.getText());

            //Validação do campos obrigatórios
            if (txtCliNome.getText().isEmpty()
                    || txtCliTelefone.getText().isEmpty()) {

                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
            } else {
                //Atualiza a tabela de clientes com os dados do formulário
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Cliente adicionado com sucesso");
                    txtCliNome.setText(null);
                    txtCliEndereco.setText(null);
                    txtCliTelefone.setText(null);
                    txtCliEmail.setText(null);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    // Pesquisa os clientes
    private void pesquisarClientes() {
        String sql = "select * from tbclientes where nomecli like ?";
        try {
            pst = conexao.prepareStatement(sql);
            // passa o conteudo para o sql
            pst.setString(1, txtPesquisar.getText() + "%");
            rs = pst.executeQuery();

            // Usa a bibliotaca rs2xml.jar para exibar na tabela
            tblClientes.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    // Preenche os dados do form com o conteudo da tabela
    public void setCampos() {
        int setar = tblClientes.getSelectedRow();
        txtID.setText(tblClientes.getModel().getValueAt(setar, 0).toString());
        txtCliNome.setText(tblClientes.getModel().getValueAt(setar, 1).toString());
        txtCliEndereco.setText(tblClientes.getModel().getValueAt(setar, 2).toString());
        txtCliTelefone.setText(tblClientes.getModel().getValueAt(setar, 3).toString());
        txtCliEmail.setText(tblClientes.getModel().getValueAt(setar, 4).toString());
    }

    // Altera os dados do cliente
    private void alterar() {
        String sql = "update tbclientes set nomecli=?,endcli=?,fonecli=?,emailcli=? where idcli=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtCliNome.getText());
            pst.setString(2, txtCliEndereco.getText());
            pst.setString(3, txtCliTelefone.getText());
            pst.setString(4, txtCliEmail.getText());
            pst.setString(5, txtID.getText());

            // Validação dos campos obrigatorios
            if (txtCliNome.getText().isEmpty()
                    || txtCliTelefone.getText().isEmpty()) {

                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
            } else {
                int add = pst.executeUpdate();
                if (add > 0) {
                    JOptionPane.showMessageDialog(null, "Dados do cliente alterado");
                    txtCliNome.setText(null);
                    txtCliEndereco.setText(null);
                    txtCliTelefone.setText(null);
                    txtCliEmail.setText(null);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }
    private void excluir(){
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        txtCliNome = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtCliEmail = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtCliEndereco = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtCliTelefone = new javax.swing.JTextField();
        btnAdicionar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        txtPesquisar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();

        setIconifiable(true);
        setMaximizable(true);
        setTitle("Clientes");
        setPreferredSize(new java.awt.Dimension(641, 480));

        jLabel2.setForeground(new java.awt.Color(255, 0, 44));
        jLabel2.setText("* Campo obrigatório");

        jLabel4.setText("Nome");

        jLabel5.setForeground(new java.awt.Color(255, 0, 44));
        jLabel5.setText("*");

        jLabel6.setText("Endereço");

        jLabel7.setText("Telefone");

        jLabel8.setForeground(new java.awt.Color(255, 0, 44));
        jLabel8.setText("*");

        jLabel9.setText("E-MAIL");

        btnAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/infoX/icones/create.png"))); // NOI18N
        btnAdicionar.setToolTipText("Adicionar");
        btnAdicionar.setPreferredSize(new java.awt.Dimension(74, 74));
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/infoX/icones/delete.png"))); // NOI18N
        btnExcluir.setToolTipText("Excluir");
        btnExcluir.setPreferredSize(new java.awt.Dimension(74, 74));
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/infoX/icones/update.png"))); // NOI18N
        btnEditar.setToolTipText("Editar");
        btnEditar.setPreferredSize(new java.awt.Dimension(74, 74));
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        txtPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPesquisarActionPerformed(evt);
            }
        });
        txtPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisarKeyReleased(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/infoX/icones/pesquisar.png"))); // NOI18N
        jLabel1.setToolTipText("Pesquisar");

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblClientes);

        jLabel3.setText("ID Cliente");

        txtID.setEditable(false);
        txtID.setEnabled(false);
        txtID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(475, 475, 475)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(1, 1, 1)
                                .addComponent(txtCliEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCliEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCliTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtCliNome, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(54, 54, 54)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(925, 925, 925))
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(txtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel8)
                    .addComponent(txtCliNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtCliEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(txtCliTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCliEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        setBounds(0, 0, 641, 480);
    }// </editor-fold>//GEN-END:initComponents

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        excluir();
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void txtPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPesquisarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesquisarActionPerformed

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        adicionar();
    }//GEN-LAST:event_btnAdicionarActionPerformed

    // enquanto faz a pesquisa atualiza a tabela
    private void txtPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarKeyReleased
        pesquisarClientes();
    }//GEN-LAST:event_txtPesquisarKeyReleased

    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseClicked
        setCampos();
    }//GEN-LAST:event_tblClientesMouseClicked

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        alterar();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void txtIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTextField txtCliEmail;
    private javax.swing.JTextField txtCliEndereco;
    private javax.swing.JTextField txtCliNome;
    private javax.swing.JTextField txtCliTelefone;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtPesquisar;
    // End of variables declaration//GEN-END:variables
}
