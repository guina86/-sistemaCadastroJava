/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.old;

import db.CidadeDao;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import modelo.Cidade;
import utilitarios.CidadeTM;

/**
 *
 * @author Leandro Guina
 */
public class JanelaCidadeOld extends javax.swing.JFrame {

    private final CidadeDao cidadeDao;
    private int current;
    private String modoEdicao;
    private final CidadeTM modelo;

    /**
     * Creates new form Cidade
     */
    public JanelaCidadeOld() {
        cidadeDao = new CidadeDao();
        current = 0;
        modoEdicao = "";
        modelo = new CidadeTM(cidadeDao.getAll());
        initComponents();
        preencherTabela();
        preencherComboEstado();
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        campoId = new javax.swing.JTextField();
        campoNome = new javax.swing.JTextField();
        botaoNovo = new javax.swing.JButton();
        botaoSalva = new javax.swing.JButton();
        botaoEdita = new javax.swing.JButton();
        botaoApaga = new javax.swing.JButton();
        botaoVolta = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaCidade = new javax.swing.JTable();
        botaoPrimeiro = new javax.swing.JButton();
        botaoProximo = new javax.swing.JButton();
        botaoUltimo = new javax.swing.JButton();
        botaoAnterior = new javax.swing.JButton();
        botaoCancela = new javax.swing.JButton();
        comboEstado = new javax.swing.JComboBox<>();
        botaoNovoEstado = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de estado");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Formulário de cadastro de cidades", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        jLabel1.setText("Id:");

        jLabel2.setText("Nome:");

        jLabel3.setText("Estado:");

        campoId.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        campoId.setEnabled(false);

        campoNome.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        campoNome.setEnabled(false);

        botaoNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/add3.png"))); // NOI18N
        botaoNovo.setToolTipText("Novo");
        botaoNovo.setMaximumSize(new java.awt.Dimension(50, 50));
        botaoNovo.setMinimumSize(new java.awt.Dimension(50, 50));
        botaoNovo.setPreferredSize(new java.awt.Dimension(50, 50));
        botaoNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoNovoActionPerformed(evt);
            }
        });

        botaoSalva.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/save2.png"))); // NOI18N
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

        botaoEdita.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/edit.png"))); // NOI18N
        botaoEdita.setToolTipText("Editar");
        botaoEdita.setEnabled(false);
        botaoEdita.setMaximumSize(new java.awt.Dimension(50, 50));
        botaoEdita.setMinimumSize(new java.awt.Dimension(50, 50));
        botaoEdita.setPreferredSize(new java.awt.Dimension(50, 50));
        botaoEdita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoEditaActionPerformed(evt);
            }
        });

        botaoApaga.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/delete.png"))); // NOI18N
        botaoApaga.setToolTipText("Apagar");
        botaoApaga.setEnabled(false);
        botaoApaga.setMaximumSize(new java.awt.Dimension(50, 50));
        botaoApaga.setMinimumSize(new java.awt.Dimension(50, 50));
        botaoApaga.setPreferredSize(new java.awt.Dimension(50, 50));
        botaoApaga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoApagaActionPerformed(evt);
            }
        });

        botaoVolta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/exit.png"))); // NOI18N
        botaoVolta.setToolTipText("Voltar");
        botaoVolta.setMaximumSize(new java.awt.Dimension(50, 50));
        botaoVolta.setMinimumSize(new java.awt.Dimension(50, 50));
        botaoVolta.setPreferredSize(new java.awt.Dimension(50, 50));
        botaoVolta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoVoltaActionPerformed(evt);
            }
        });

        tabelaCidade.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tabelaCidade.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaCidadeMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaCidade);

        botaoPrimeiro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/primeiro.png"))); // NOI18N
        botaoPrimeiro.setToolTipText("Primeiro");
        botaoPrimeiro.setMaximumSize(new java.awt.Dimension(50, 50));
        botaoPrimeiro.setMinimumSize(new java.awt.Dimension(50, 50));
        botaoPrimeiro.setPreferredSize(new java.awt.Dimension(50, 50));
        botaoPrimeiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoPrimeiroActionPerformed(evt);
            }
        });

        botaoProximo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/proximo.png"))); // NOI18N
        botaoProximo.setToolTipText("Próximo");
        botaoProximo.setMaximumSize(new java.awt.Dimension(50, 50));
        botaoProximo.setMinimumSize(new java.awt.Dimension(50, 50));
        botaoProximo.setPreferredSize(new java.awt.Dimension(50, 50));
        botaoProximo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoProximoActionPerformed(evt);
            }
        });

        botaoUltimo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/ultimo.png"))); // NOI18N
        botaoUltimo.setToolTipText("Ultimo");
        botaoUltimo.setMaximumSize(new java.awt.Dimension(50, 50));
        botaoUltimo.setMinimumSize(new java.awt.Dimension(50, 50));
        botaoUltimo.setPreferredSize(new java.awt.Dimension(50, 50));
        botaoUltimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoUltimoActionPerformed(evt);
            }
        });

        botaoAnterior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/anterior.png"))); // NOI18N
        botaoAnterior.setToolTipText("Anterior");
        botaoAnterior.setMaximumSize(new java.awt.Dimension(50, 50));
        botaoAnterior.setMinimumSize(new java.awt.Dimension(50, 50));
        botaoAnterior.setPreferredSize(new java.awt.Dimension(50, 50));
        botaoAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAnteriorActionPerformed(evt);
            }
        });

        botaoCancela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/cancel.png"))); // NOI18N
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

        comboEstado.setEnabled(false);
        comboEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboEstadoActionPerformed(evt);
            }
        });

        botaoNovoEstado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/addSmall.png"))); // NOI18N
        botaoNovoEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoNovoEstadoActionPerformed(evt);
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
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botaoNovoEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoId, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                .addComponent(botaoVolta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(botaoPrimeiro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botaoAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botaoProximo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botaoUltimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(campoId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(campoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(comboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(botaoNovoEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
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

    private void botaoVoltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoVoltaActionPerformed
        this.dispose();
    }//GEN-LAST:event_botaoVoltaActionPerformed

    private void botaoApagaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoApagaActionPerformed
        int option = JOptionPane.showConfirmDialog(rootPane, "Tem certeza que quer excluir " + campoNome.getText(), "Confirmação", JOptionPane.YES_NO_OPTION);
        if (option == 0) {
            int id = Integer.parseInt(campoId.getText());
            cidadeDao.delete(id);
            modelo.remove(current);
        }
        setInterface(false, false, true, false, false, false, true, false, true, true);
    }//GEN-LAST:event_botaoApagaActionPerformed

    private void botaoSalvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSalvaActionPerformed
        if (modoEdicao.equals("salvar")) {
            Cidade cidade = new Cidade(campoNome.getText(), comboEstado.getSelectedItem().toString());
            int id = cidadeDao.save(cidade);
            cidade.setId(id);
            modelo.add(cidade);
        } else if (modoEdicao.equals("update")) {
            Cidade cidade = new Cidade(Integer.valueOf(campoId.getText()), campoNome.getText(), comboEstado.getSelectedItem().toString());
            cidadeDao.update(cidade);
            modelo.setValueAt(cidade, current);
        }
        setInterface(false, false, true, false, false, false, true, false, true, true);
    }//GEN-LAST:event_botaoSalvaActionPerformed

    private void botaoNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoNovoActionPerformed
        modoEdicao = "salvar";
        preencherComboEstado();
        tabelaCidade.setRowSelectionAllowed(false);
        setInterface(true, true, false, true, false, false, false, true, false, true);
        campoNome.requestFocus();
    }//GEN-LAST:event_botaoNovoActionPerformed

    private void botaoPrimeiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoPrimeiroActionPerformed
        current = 0;
        tabelaCidade.setRowSelectionInterval(current, current);
        Cidade cidade = modelo.get(0);
        preencherCampos(cidade);
        setInterface(false, false, true, false, true, true, true, false, true, false);
    }//GEN-LAST:event_botaoPrimeiroActionPerformed

    private void botaoUltimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoUltimoActionPerformed
        current = modelo.getRowCount() - 1;
        tabelaCidade.setRowSelectionInterval(current, current);
        Cidade cidade = modelo.get(current);
        preencherCampos(cidade);
        setInterface(false, false, true, false, true, true, true, false, true, false);
    }//GEN-LAST:event_botaoUltimoActionPerformed

    private void botaoAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAnteriorActionPerformed
        if (current > 0) {
            current--;
        }
        tabelaCidade.setRowSelectionInterval(current, current);
        Cidade cidade = modelo.get(current);
        preencherCampos(cidade);
        setInterface(false, false, true, false, true, true, true, false, true, false);
    }//GEN-LAST:event_botaoAnteriorActionPerformed

    private void botaoProximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoProximoActionPerformed
        int size = modelo.getRowCount();
        if (campoId.getText().equals("")) {
            current = 0;
        } else if (size > 1 && current < size - 1) {
            current++;
        }
        tabelaCidade.setRowSelectionInterval(current, current);
        Cidade cidade = modelo.get(current);
        preencherCampos(cidade);
        setInterface(false, false, true, false, true, true, true, false, true, false);
    }//GEN-LAST:event_botaoProximoActionPerformed

    private void botaoEditaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoEditaActionPerformed
        modoEdicao = "update";
        Object o = comboEstado.getSelectedItem();
        preencherComboEstado();
        comboEstado.setSelectedItem(o);
        setInterface(true, true, false, true, false, false, false, true, true, false);
        campoNome.requestFocus();
    }//GEN-LAST:event_botaoEditaActionPerformed

    private void botaoCancelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelaActionPerformed
        setInterface(false, false, true, false, false, false, true, false, true, true);
    }//GEN-LAST:event_botaoCancelaActionPerformed

    private void tabelaCidadeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaCidadeMouseClicked
        current = tabelaCidade.getSelectedRow();
        Cidade cidade = modelo.get(current);
        preencherCampos(cidade);
        setInterface(false, false, true, false, true, true, true, false, true, false);
    }//GEN-LAST:event_tabelaCidadeMouseClicked

    private void comboEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboEstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboEstadoActionPerformed

    private void botaoNovoEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoNovoEstadoActionPerformed
        JanelaEstadoOld janelaEstado = new JanelaEstadoOld();
        janelaEstado.setVisible(true);
    }//GEN-LAST:event_botaoNovoEstadoActionPerformed

    private void setInterface(boolean nome, boolean estado, boolean novo, boolean salva, boolean edita, boolean apaga, boolean navegacao, boolean cancela, boolean tabela, boolean limpaCampos) {
        limpaCampos(limpaCampos);
        campoNome.setEnabled(nome);
        comboEstado.setEnabled(estado);
        botaoSalva.setEnabled(salva);
        botaoEdita.setEnabled(edita);
        botaoApaga.setEnabled(apaga);
        botaoNovo.setEnabled(novo);
        botaoCancela.setEnabled(cancela);
        botaoPrimeiro.setEnabled(navegacao);
        botaoAnterior.setEnabled(navegacao);
        botaoProximo.setEnabled(navegacao);
        botaoUltimo.setEnabled(navegacao);
        tabelaCidade.setRowSelectionAllowed(tabela);
    }

    private void preencherComboEstado(){
        comboEstado.removeAllItems();
        comboEstado.addItem("");
        cidadeDao.estados().forEach(s-> comboEstado.addItem(s));
    }
    
    private void preencherCampos(Cidade cidade) {
        campoId.setText(cidade.getId().toString());
        campoNome.setText(cidade.getNome());
        comboEstado.setSelectedItem(cidade.getEstado());
    }

    private void limpaCampos(boolean opcao) {
        if (opcao) {
            campoId.setText("");
            campoNome.setText("");
            comboEstado.setSelectedIndex(0);
            tabelaCidade.clearSelection();
        }
    }

    private void preencherTabela() {
        tabelaCidade.setModel(modelo);
        tabelaCidade.getColumnModel().getColumn(0).setPreferredWidth(40);
        tabelaCidade.getColumnModel().getColumn(0).setResizable(false);
        tabelaCidade.getColumnModel().getColumn(1).setPreferredWidth(200);
        tabelaCidade.getColumnModel().getColumn(1).setResizable(false);
        tabelaCidade.getColumnModel().getColumn(2).setPreferredWidth(200);
        tabelaCidade.getColumnModel().getColumn(2).setResizable(false);
        tabelaCidade.getTableHeader().setReorderingAllowed(false);
        tabelaCidade.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabelaCidade.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelaCidade.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

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
//        try {
//            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(JanelaCidadeOld.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(JanelaCidadeOld.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(JanelaCidadeOld.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(JanelaCidadeOld.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JanelaCidadeOld().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoAnterior;
    private javax.swing.JButton botaoApaga;
    private javax.swing.JButton botaoCancela;
    private javax.swing.JButton botaoEdita;
    private javax.swing.JButton botaoNovo;
    private javax.swing.JButton botaoNovoEstado;
    private javax.swing.JButton botaoPrimeiro;
    private javax.swing.JButton botaoProximo;
    private javax.swing.JButton botaoSalva;
    private javax.swing.JButton botaoUltimo;
    private javax.swing.JButton botaoVolta;
    private javax.swing.JTextField campoId;
    private javax.swing.JTextField campoNome;
    private javax.swing.JComboBox<String> comboEstado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaCidade;
    // End of variables declaration//GEN-END:variables
}