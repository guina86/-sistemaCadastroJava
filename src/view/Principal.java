/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author Leandro Guina
 */
public class Principal extends javax.swing.JFrame {

    /**
     * Creates new form Principal
     */
    public Principal() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        menuCadastro = new javax.swing.JMenu();
        itemMenuEstado = new javax.swing.JMenuItem();
        itemMenuCidade = new javax.swing.JMenuItem();
        itemMenuBairro = new javax.swing.JMenuItem();
        itemMenuTelefone = new javax.swing.JMenuItem();
        itemMenuCliente = new javax.swing.JMenuItem();
        itemMenuFornecedor = new javax.swing.JMenuItem();
        itemMenuProduto = new javax.swing.JMenuItem();
        menuCompra = new javax.swing.JMenu();
        menuVenda = new javax.swing.JMenu();
        menuRelatorio = new javax.swing.JMenu();
        menuSair = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de vendas");

        menuCadastro.setText("Cadastros");

        itemMenuEstado.setText("Estados");
        itemMenuEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemMenuEstadoActionPerformed(evt);
            }
        });
        menuCadastro.add(itemMenuEstado);

        itemMenuCidade.setText("Cidades");
        itemMenuCidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemMenuCidadeActionPerformed(evt);
            }
        });
        menuCadastro.add(itemMenuCidade);

        itemMenuBairro.setText("Bairro");
        itemMenuBairro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemMenuBairroActionPerformed(evt);
            }
        });
        menuCadastro.add(itemMenuBairro);

        itemMenuTelefone.setText("Telefones");
        itemMenuTelefone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemMenuTelefoneActionPerformed(evt);
            }
        });
        menuCadastro.add(itemMenuTelefone);

        itemMenuCliente.setText("Clientes");
        itemMenuCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemMenuClienteActionPerformed(evt);
            }
        });
        menuCadastro.add(itemMenuCliente);

        itemMenuFornecedor.setText("Fornecedores");
        itemMenuFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemMenuFornecedorActionPerformed(evt);
            }
        });
        menuCadastro.add(itemMenuFornecedor);

        itemMenuProduto.setText("Produtos");
        menuCadastro.add(itemMenuProduto);

        jMenuBar1.add(menuCadastro);

        menuCompra.setText("Compra");
        jMenuBar1.add(menuCompra);

        menuVenda.setText("Venda");
        jMenuBar1.add(menuVenda);

        menuRelatorio.setText("Relatórios");
        jMenuBar1.add(menuRelatorio);

        menuSair.setText("Sair");
        menuSair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuSairMouseClicked(evt);
            }
        });
        jMenuBar1.add(menuSair);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 619, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 492, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(800, 600));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void menuSairMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuSairMouseClicked
        this.dispose();
    }//GEN-LAST:event_menuSairMouseClicked

    private void itemMenuEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMenuEstadoActionPerformed
        DialogEstado dialog = new DialogEstado(this, true);
        dialog.setVisible(true);
    }//GEN-LAST:event_itemMenuEstadoActionPerformed

    private void itemMenuCidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMenuCidadeActionPerformed
        DialogCidade dialog = new DialogCidade(this, true);
        dialog.setVisible(true);
    }//GEN-LAST:event_itemMenuCidadeActionPerformed

    private void itemMenuBairroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMenuBairroActionPerformed
        DialogBairro dialog = new DialogBairro(this, true);
        dialog.setVisible(true);
    }//GEN-LAST:event_itemMenuBairroActionPerformed

    private void itemMenuTelefoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMenuTelefoneActionPerformed
        DialogTelefone dialog = new DialogTelefone(this, true);
        dialog.setVisible(true);
    }//GEN-LAST:event_itemMenuTelefoneActionPerformed

    private void itemMenuClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMenuClienteActionPerformed
        DialogCliente dialog = new DialogCliente(this, true);
        dialog.setVisible(true);
    }//GEN-LAST:event_itemMenuClienteActionPerformed

    private void itemMenuFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMenuFornecedorActionPerformed
        DialogFornecedor dialog = new DialogFornecedor(this, true);
        dialog.setVisible(true);
    }//GEN-LAST:event_itemMenuFornecedorActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }

        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem itemMenuBairro;
    private javax.swing.JMenuItem itemMenuCidade;
    private javax.swing.JMenuItem itemMenuCliente;
    private javax.swing.JMenuItem itemMenuEstado;
    private javax.swing.JMenuItem itemMenuFornecedor;
    private javax.swing.JMenuItem itemMenuProduto;
    private javax.swing.JMenuItem itemMenuTelefone;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu menuCadastro;
    private javax.swing.JMenu menuCompra;
    private javax.swing.JMenu menuRelatorio;
    private javax.swing.JMenu menuSair;
    private javax.swing.JMenu menuVenda;
    // End of variables declaration//GEN-END:variables
}
