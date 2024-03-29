/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import db.EstadoDao;
import java.awt.Frame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import modelo.Estado;
import utilitarios.EstadoTM;

/**
 *
 * @author Leandro Guina
 */
public class DialogEstado extends javax.swing.JDialog {

    private final EstadoDao estadoDao;
    private final EstadoTM modeloTabela;
    private static final int QWERY = 0;
    private static final int INSERT = 1;
    private static final int UPDATE = 2;
    private int current;
    private int modo;

    /**
     * Creates new form DialogEstado
     */
    public DialogEstado(Frame owner, boolean modal) {
        super(owner, modal);
        estadoDao = new EstadoDao();
        modeloTabela = new EstadoTM(estadoDao.getAll());
        initComponents();
        inicializa();
    }

    private void inicializa() {
        // variaveis globais
        current = 0;
        modo = QWERY;

        // tabela
        tabelaEstado.setModel(modeloTabela);
        tabelaEstado.getColumnModel().getColumn(0).setPreferredWidth(80);
        tabelaEstado.getColumnModel().getColumn(0).setResizable(false);
        tabelaEstado.getColumnModel().getColumn(1).setPreferredWidth(300);
        tabelaEstado.getColumnModel().getColumn(1).setResizable(false);
        tabelaEstado.getColumnModel().getColumn(2).setPreferredWidth(80);
        tabelaEstado.getColumnModel().getColumn(2).setResizable(false);
        tabelaEstado.getTableHeader().setReorderingAllowed(false);
        tabelaEstado.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelaEstado.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    }

    private void setModo(int modo) {
        switch (modo) {
            case QWERY:
                this.modo = modo;
                setInterface(false, false, true, false, true, true, true, false, true);
                break;
            case INSERT:
                this.modo = modo;
                setInterface(true, true, false, true, false, false, false, true, false);
                campoNome.setText("");
                campoSigla.setText("");
                campoNome.requestFocus();
                break;
            case UPDATE:
                this.modo = modo;
                setInterface(true, true, false, true, false, false, false, true, false);
                campoNome.requestFocus();
                break;
            default:
                break;
        }
    }

    private void setInterface(boolean nome, boolean sigla, boolean novo, boolean salva, boolean edita, boolean apaga, boolean navegacao, boolean cancela, boolean tabela) {
        campoNome.setEnabled(nome);
        campoSigla.setEnabled(sigla);
        botaoSalva.setEnabled(salva);
        botaoEdita.setEnabled(edita);
        botaoApaga.setEnabled(apaga);
        botaoNovo.setEnabled(novo);
        botaoCancela.setEnabled(cancela);
        botaoPrimeiro.setEnabled(navegacao);
        botaoAnterior.setEnabled(navegacao);
        botaoProximo.setEnabled(navegacao);
        botaoUltimo.setEnabled(navegacao);
        tabelaEstado.setRowSelectionAllowed(tabela);
        tabelaEstado.setFocusable(tabela);
    }

    private void setCurrent(int current) {
        this.current = current;
        preencheCampos();
    }

    private void preencheCampos() {
        Estado estado = modeloTabela.get(current);
        campoNome.setText(estado.getNome());
        campoSigla.setText(estado.getSigla());
    }

    private boolean validaCampos() {
        if (campoNome.getText().equals("") || campoSigla.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Confira os dados selecionados, registro incompleto", "Resgisto incompleto", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    private Estado colectaCampos() {
        int id = 0;
        if (!modeloTabela.isEmpty()) {
            id = modeloTabela.get(current).getId();
        }
        return new Estado(id, campoNome.getText(), campoSigla.getText());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        labelNome = new javax.swing.JLabel();
        labelSigla = new javax.swing.JLabel();
        campoNome = new javax.swing.JTextField();
        campoSigla = new javax.swing.JTextField();
        botaoNovo = new javax.swing.JButton();
        botaoSalva = new javax.swing.JButton();
        botaoEdita = new javax.swing.JButton();
        botaoApaga = new javax.swing.JButton();
        botaoVolta = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaEstado = new javax.swing.JTable();
        botaoPrimeiro = new javax.swing.JButton();
        botaoProximo = new javax.swing.JButton();
        botaoUltimo = new javax.swing.JButton();
        botaoAnterior = new javax.swing.JButton();
        botaoCancela = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cadastro de Estados", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        labelNome.setText("Nome:");

        labelSigla.setText("Sigla:");

        campoNome.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        campoNome.setEnabled(false);

        campoSigla.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        campoSigla.setEnabled(false);

        botaoNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/add_36p.png"))); // NOI18N
        botaoNovo.setToolTipText("Novo");
        botaoNovo.setMaximumSize(new java.awt.Dimension(50, 50));
        botaoNovo.setMinimumSize(new java.awt.Dimension(50, 50));
        botaoNovo.setPreferredSize(new java.awt.Dimension(50, 50));
        botaoNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoNovoActionPerformed(evt);
            }
        });

        botaoSalva.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/save_36p.png"))); // NOI18N
        botaoSalva.setToolTipText("Salvar");
        botaoSalva.setEnabled(false);
        botaoSalva.setMaximumSize(new java.awt.Dimension(50, 50));
        botaoSalva.setMinimumSize(new java.awt.Dimension(50, 50));
        botaoSalva.setPreferredSize(new java.awt.Dimension(50, 50));
        botaoSalva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSalvaActionPerformed(evt);
            }
        });

        botaoEdita.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/edit_36p.png"))); // NOI18N
        botaoEdita.setToolTipText("Editar");
        botaoEdita.setMaximumSize(new java.awt.Dimension(50, 50));
        botaoEdita.setMinimumSize(new java.awt.Dimension(50, 50));
        botaoEdita.setPreferredSize(new java.awt.Dimension(50, 50));
        botaoEdita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoEditaActionPerformed(evt);
            }
        });

        botaoApaga.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/delete_36p.png"))); // NOI18N
        botaoApaga.setToolTipText("Apagar");
        botaoApaga.setMaximumSize(new java.awt.Dimension(50, 50));
        botaoApaga.setMinimumSize(new java.awt.Dimension(50, 50));
        botaoApaga.setPreferredSize(new java.awt.Dimension(50, 50));
        botaoApaga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoApagaActionPerformed(evt);
            }
        });

        botaoVolta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/exit_36p.png"))); // NOI18N
        botaoVolta.setToolTipText("Voltar");
        botaoVolta.setMaximumSize(new java.awt.Dimension(50, 50));
        botaoVolta.setMinimumSize(new java.awt.Dimension(50, 50));
        botaoVolta.setPreferredSize(new java.awt.Dimension(50, 50));
        botaoVolta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoVoltaActionPerformed(evt);
            }
        });

        tabelaEstado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tabelaEstado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaEstadoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaEstado);

        botaoPrimeiro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/first_36p.png"))); // NOI18N
        botaoPrimeiro.setToolTipText("Primeiro");
        botaoPrimeiro.setMaximumSize(new java.awt.Dimension(50, 50));
        botaoPrimeiro.setMinimumSize(new java.awt.Dimension(50, 50));
        botaoPrimeiro.setPreferredSize(new java.awt.Dimension(50, 50));
        botaoPrimeiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoPrimeiroActionPerformed(evt);
            }
        });

        botaoProximo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/right_36.png"))); // NOI18N
        botaoProximo.setToolTipText("Próximo");
        botaoProximo.setMaximumSize(new java.awt.Dimension(50, 50));
        botaoProximo.setMinimumSize(new java.awt.Dimension(50, 50));
        botaoProximo.setPreferredSize(new java.awt.Dimension(50, 50));
        botaoProximo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoProximoActionPerformed(evt);
            }
        });

        botaoUltimo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/last_36p.png"))); // NOI18N
        botaoUltimo.setToolTipText("Ultimo");
        botaoUltimo.setMaximumSize(new java.awt.Dimension(50, 50));
        botaoUltimo.setMinimumSize(new java.awt.Dimension(50, 50));
        botaoUltimo.setPreferredSize(new java.awt.Dimension(50, 50));
        botaoUltimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoUltimoActionPerformed(evt);
            }
        });

        botaoAnterior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/left_36p.png"))); // NOI18N
        botaoAnterior.setToolTipText("Anterior");
        botaoAnterior.setMaximumSize(new java.awt.Dimension(50, 50));
        botaoAnterior.setMinimumSize(new java.awt.Dimension(50, 50));
        botaoAnterior.setPreferredSize(new java.awt.Dimension(50, 50));
        botaoAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAnteriorActionPerformed(evt);
            }
        });

        botaoCancela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/cancel_36p.png"))); // NOI18N
        botaoCancela.setToolTipText("Voltar");
        botaoCancela.setEnabled(false);
        botaoCancela.setMaximumSize(new java.awt.Dimension(50, 50));
        botaoCancela.setMinimumSize(new java.awt.Dimension(50, 50));
        botaoCancela.setPreferredSize(new java.awt.Dimension(50, 50));
        botaoCancela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCancelaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(botaoPrimeiro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botaoAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botaoProximo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botaoUltimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(labelNome)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48)
                                .addComponent(labelSigla)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoSigla, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(botaoNovo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(botaoSalva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(botaoEdita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(botaoApaga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(botaoCancela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(botaoVolta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelNome)
                    .addComponent(campoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSigla)
                    .addComponent(campoSigla, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(botaoApaga, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botaoEdita, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botaoSalva, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botaoNovo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(botaoCancela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoVolta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(botaoAnterior, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botaoPrimeiro, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(botaoUltimo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botaoProximo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void botaoNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoNovoActionPerformed
        setModo(INSERT);
    }//GEN-LAST:event_botaoNovoActionPerformed

    private void botaoSalvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSalvaActionPerformed
        if (validaCampos()) {
            Estado estado = colectaCampos();
            if (modo == INSERT) {
                int id = estadoDao.save(estado);
                estado.setId(id);
                modeloTabela.add(estado);
                setCurrent(modeloTabela.getRowCount() - 1);
                tabelaEstado.setRowSelectionInterval(current, current);
            } else if (modo == UPDATE) {
                estadoDao.update(estado);
                modeloTabela.setValueAt(estado, current);
            }
            setModo(QWERY);
        }

    }//GEN-LAST:event_botaoSalvaActionPerformed

    private void botaoEditaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoEditaActionPerformed
        if (tabelaEstado.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Nenhum registro selecionado", "Opção inválida", JOptionPane.WARNING_MESSAGE);
        } else {
            setModo(UPDATE);
        }
    }//GEN-LAST:event_botaoEditaActionPerformed

    private void botaoApagaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoApagaActionPerformed
        if (tabelaEstado.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Nenhum registro selecionado", "Opção inválida", JOptionPane.WARNING_MESSAGE);
        } else {
            int option = JOptionPane.showConfirmDialog(rootPane, "Tem certeza que quer excluir " + campoNome.getText() + " " + campoSigla.getText(), "Confirmação", JOptionPane.YES_NO_OPTION);
            if (option == 0) {
                estadoDao.delete(modeloTabela.get(current));
                modeloTabela.remove(current);
                campoNome.setText("");
            }
        }
    }//GEN-LAST:event_botaoApagaActionPerformed

    private void botaoVoltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoVoltaActionPerformed
        this.dispose();
    }//GEN-LAST:event_botaoVoltaActionPerformed

    private void tabelaEstadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaEstadoMouseClicked
        if (modo == QWERY) {
            setCurrent(tabelaEstado.getSelectedRow());
        }
    }//GEN-LAST:event_tabelaEstadoMouseClicked

    private void botaoPrimeiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoPrimeiroActionPerformed
        setCurrent(0);
        tabelaEstado.setRowSelectionInterval(current, current);
    }//GEN-LAST:event_botaoPrimeiroActionPerformed

    private void botaoProximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoProximoActionPerformed
        int size = modeloTabela.getRowCount();
        if (tabelaEstado.getSelectedRow() == -1) {
            setCurrent(0);
        } else if (size > 1 && current < size - 1) {
            setCurrent(current + 1);
        }
        tabelaEstado.setRowSelectionInterval(current, current);
    }//GEN-LAST:event_botaoProximoActionPerformed

    private void botaoUltimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoUltimoActionPerformed
        setCurrent(modeloTabela.getRowCount() - 1);
        tabelaEstado.setRowSelectionInterval(current, current);
    }//GEN-LAST:event_botaoUltimoActionPerformed

    private void botaoAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAnteriorActionPerformed
        if (current > 0) {
            setCurrent(current - 1);
        }
        tabelaEstado.setRowSelectionInterval(current, current);
    }//GEN-LAST:event_botaoAnteriorActionPerformed

    private void botaoCancelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelaActionPerformed
        if (modo == INSERT) {
            campoNome.setText("");
            campoSigla.setText("");
        } else {
            preencheCampos();
        }
        setModo(QWERY);
    }//GEN-LAST:event_botaoCancelaActionPerformed

    public Estado edtitEstado() {
        setVisible(true);
        return modeloTabela.get(current);
    }

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DialogEstado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DialogEstado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DialogEstado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DialogEstado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DialogEstado dialog = new DialogEstado(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoAnterior;
    private javax.swing.JButton botaoApaga;
    private javax.swing.JButton botaoCancela;
    private javax.swing.JButton botaoEdita;
    private javax.swing.JButton botaoNovo;
    private javax.swing.JButton botaoPrimeiro;
    private javax.swing.JButton botaoProximo;
    private javax.swing.JButton botaoSalva;
    private javax.swing.JButton botaoUltimo;
    private javax.swing.JButton botaoVolta;
    private javax.swing.JTextField campoNome;
    private javax.swing.JTextField campoSigla;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelNome;
    private javax.swing.JLabel labelSigla;
    private javax.swing.JTable tabelaEstado;
    // End of variables declaration//GEN-END:variables
}
