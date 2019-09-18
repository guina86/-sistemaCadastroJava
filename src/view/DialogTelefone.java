/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import db.TelefoneDao;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import modelo.Telefone;
import utilitarios.TelefoneTM;

/**
 *
 * @author Leandro Guina
 */
public class DialogTelefone extends javax.swing.JDialog implements ActionListener {

    private final TelefoneDao telefoneDao;
    private final TelefoneTM modeloTabela;
    private final DefaultComboBoxModel modeloCombo;
    private static final int QWERY = 0;
    private static final int INSERT = 1;
    private static final int UPDATE = 2;
    private static final int DIALOG = 3;
    private int current;
    private int modo;
    private boolean comboLock;

    /**
     * Creates new form DialogTelefone
     */
    public DialogTelefone() {
        telefoneDao = new TelefoneDao();
        modeloTabela = new TelefoneTM(telefoneDao.getAll());
        modeloCombo = new DefaultComboBoxModel();
        initComponents();
        inicializa();
    }

    public DialogTelefone(Frame owner, boolean modal) {
        super(owner, modal);
        telefoneDao = new TelefoneDao();
        modeloTabela = new TelefoneTM();
        modeloCombo = new DefaultComboBoxModel();
        initComponents();
        inicializa();
    }

    private void inicializa() {
        current = 0;
        modo = QWERY;
        comboLock = false;
        comboNome.setModel(modeloCombo);
        comboNome.addActionListener(this);
        tabelaTelefone.setModel(modeloTabela);
        tabelaTelefone.getColumnModel().getColumn(0).setPreferredWidth(40);
        tabelaTelefone.getColumnModel().getColumn(0).setResizable(false);
        tabelaTelefone.getColumnModel().getColumn(1).setPreferredWidth(200);
        tabelaTelefone.getColumnModel().getColumn(1).setResizable(false);
        tabelaTelefone.getTableHeader().setReorderingAllowed(false);
        tabelaTelefone.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelaTelefone.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    }

    private void setModo(int modo) {
        switch (modo) {
            case QWERY:
                this.modo = modo;
                setInterface(false, true, false, true, true, true, false, true, true, true);
                break;
            case INSERT:
                this.modo = modo;
                setInterface(true, false, true, false, false, false, true, false, false, true);
                break;
            case UPDATE:
                this.modo = modo;
                setInterface(true, false, true, false, false, false, true, false, true, false);
                break;
            case DIALOG:
                this.modo = modo;
                setInterface(false, false, true, true, true, true, false, true, false, true);
                break;
            default:
                break;
        }
    }

    private void setInterface(boolean nome, boolean novo, boolean salva, boolean edita, boolean apaga, boolean navegacao, boolean cancela, boolean tabela, boolean combo, boolean limpaCampos) {
        limpaCampos(limpaCampos);
        campoNumero.setEnabled(nome);
        botaoSalva.setEnabled(salva);
        botaoEdita.setEnabled(edita);
        botaoApaga.setEnabled(apaga);
        botaoNovo.setEnabled(novo);
        botaoCancela.setEnabled(cancela);
        botaoPrimeiro.setEnabled(navegacao);
        botaoAnterior.setEnabled(navegacao);
        botaoProximo.setEnabled(navegacao);
        botaoUltimo.setEnabled(navegacao);
        comboNome.setEnabled(combo);
        tabelaTelefone.setRowSelectionAllowed(tabela);
        tabelaTelefone.setFocusable(tabela);
    }

    private void preencheCampos() {
        Telefone telefone = modeloTabela.get(current);
        campoNumero.setText(telefone.getNumero());
    }

    private void limpaCampos(boolean opcao) {
        if (opcao) {
            campoNumero.setValue(null);
            tabelaTelefone.clearSelection();
        }
    }

    private void reloadCombo() {
        comboNome.removeActionListener(this);
        modeloCombo.removeAllElements();
        modeloCombo.addElement("");
        if (radioCliente.isSelected()) {
            modeloCombo.addAll(telefoneDao.clientes());
        } else if (radioFornecedor.isSelected()) {
            modeloCombo.addAll(telefoneDao.fornecedores());
        }
        comboNome.addActionListener(this);
    }

    private boolean validaCampos() {
        if (campoNumero.getValue() == null || comboNome.getSelectedItem().equals("")) {
            JOptionPane.showMessageDialog(this, "Confira os dados selecionados, registro incompleto", "Resgisto incompleto", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    private void setCurrent(int current) {
        this.current = current;
        preencheCampos();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoRadio = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        botaoNovo = new javax.swing.JButton();
        botaoSalva = new javax.swing.JButton();
        botaoEdita = new javax.swing.JButton();
        botaoApaga = new javax.swing.JButton();
        botaoVolta = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaTelefone = new javax.swing.JTable();
        botaoPrimeiro = new javax.swing.JButton();
        botaoProximo = new javax.swing.JButton();
        botaoUltimo = new javax.swing.JButton();
        botaoAnterior = new javax.swing.JButton();
        botaoCancela = new javax.swing.JButton();
        campoNumero = new javax.swing.JFormattedTextField();
        radioCliente = new javax.swing.JRadioButton();
        radioFornecedor = new javax.swing.JRadioButton();
        labelNome = new javax.swing.JLabel();
        comboNome = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cadastro de Telefones", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        jLabel2.setText("Numero:");

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

        tabelaTelefone.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tabelaTelefone.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaTelefoneMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaTelefone);

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

        try {
            campoNumero.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) ####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        campoNumero.setEnabled(false);

        grupoRadio.add(radioCliente);
        radioCliente.setText("Cliente");
        radioCliente.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                radioClienteItemStateChanged(evt);
            }
        });

        grupoRadio.add(radioFornecedor);
        radioFornecedor.setText("Fornecedor");

        labelNome.setText("Nome:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(radioCliente)
                            .addComponent(radioFornecedor))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelNome, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(campoNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboNome, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(botaoPrimeiro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoProximo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoUltimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(botaoNovo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoSalva, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botaoEdita, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botaoApaga, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botaoCancela, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botaoVolta, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(radioCliente)
                    .addComponent(labelNome)
                    .addComponent(comboNome, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel2)
                    .addComponent(campoNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(radioFornecedor))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(botaoSalva, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoEdita, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoApaga, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoNovo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoCancela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoVolta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(botaoPrimeiro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoAnterior, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                    .addComponent(botaoProximo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoUltimo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
        campoNumero.requestFocus();
    }//GEN-LAST:event_botaoNovoActionPerformed

    private void botaoSalvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSalvaActionPerformed
        if (validaCampos()) {

            if (modo == INSERT) {
                Telefone telefone = new Telefone(campoNumero.getText());
                int id = telefoneDao.save(telefone, comboNome.getSelectedItem().toString(), radioCliente.isSelected());
                telefone.setId(id);
                modeloTabela.add(telefone);
            } else if (modo == UPDATE) {
                Telefone telefone = new Telefone(modeloTabela.get(current).getId(), campoNumero.getText());
                telefoneDao.update(telefone);
                modeloTabela.setValueAt(telefone, current);
            }
        }
        setModo(QWERY);
    }//GEN-LAST:event_botaoSalvaActionPerformed

    private void botaoEditaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoEditaActionPerformed
        if (tabelaTelefone.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Nenhum registro selecionado", "Opção inválida", JOptionPane.WARNING_MESSAGE);
        } else {
            setModo(UPDATE);
            campoNumero.requestFocus();
        }
    }//GEN-LAST:event_botaoEditaActionPerformed

    private void botaoApagaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoApagaActionPerformed
        if (tabelaTelefone.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Nenhum registro selecionado", "Opção inválida", JOptionPane.WARNING_MESSAGE);
        } else {
            int option = JOptionPane.showConfirmDialog(rootPane, "Tem certeza que quer excluir " + campoNumero.getText(), "Confirmação", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                telefoneDao.delete(modeloTabela.get(current).getId());
                modeloTabela.remove(current);
            }
        }
    }//GEN-LAST:event_botaoApagaActionPerformed

    private void botaoVoltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoVoltaActionPerformed
        this.dispose();
    }//GEN-LAST:event_botaoVoltaActionPerformed

    private void tabelaTelefoneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaTelefoneMouseClicked
        if (modo == QWERY) {
            setCurrent(tabelaTelefone.getSelectedRow());
            if (modo == UPDATE) {
//            setModo(QWERY);
            }
        }
    }//GEN-LAST:event_tabelaTelefoneMouseClicked

    private void botaoPrimeiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoPrimeiroActionPerformed
        setCurrent(0);
        tabelaTelefone.setRowSelectionInterval(current, current);
    }//GEN-LAST:event_botaoPrimeiroActionPerformed

    private void botaoProximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoProximoActionPerformed
        if (!modeloTabela.isEmpty()) {
            int size = modeloTabela.getRowCount();
            if (tabelaTelefone.getSelectedRow() == -1) {
                setCurrent(0);
            } else if (size > 1 && current < size - 1) {
                setCurrent(current + 1);

            }
            tabelaTelefone.setRowSelectionInterval(current, current);
        }
    }//GEN-LAST:event_botaoProximoActionPerformed

    private void botaoUltimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoUltimoActionPerformed
        setCurrent(modeloTabela.getRowCount() - 1);
        tabelaTelefone.setRowSelectionInterval(current, current);
    }//GEN-LAST:event_botaoUltimoActionPerformed

    private void botaoAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAnteriorActionPerformed
        if (!modeloTabela.isEmpty()) {
            if (current > 0) {
                setCurrent(current - 1);
            }
            tabelaTelefone.setRowSelectionInterval(current, current);
        }
    }//GEN-LAST:event_botaoAnteriorActionPerformed

    private void botaoCancelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelaActionPerformed
        setModo(QWERY);
    }//GEN-LAST:event_botaoCancelaActionPerformed

    private void radioClienteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_radioClienteItemStateChanged
        if (radioCliente.isSelected()) {
            reloadCombo();
        }
    }//GEN-LAST:event_radioClienteItemStateChanged

    public List<String> dialog(){
        setModo(DIALOG);
        setVisible(true);
        return modeloTabela.getLista();
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == comboNome) {
            modeloTabela.limpar();
            modeloTabela.addLista(telefoneDao.numeros(comboNome.getSelectedItem().toString()));
            System.out.println(campoNumero.getText());
        }
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
            java.util.logging.Logger.getLogger(DialogTelefone.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DialogTelefone.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DialogTelefone.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DialogTelefone.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DialogTelefone dialog = new DialogTelefone(new javax.swing.JFrame(), true);
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
    private javax.swing.JFormattedTextField campoNumero;
    private javax.swing.JComboBox<String> comboNome;
    private javax.swing.ButtonGroup grupoRadio;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelNome;
    private javax.swing.JRadioButton radioCliente;
    private javax.swing.JRadioButton radioFornecedor;
    private javax.swing.JTable tabelaTelefone;
    // End of variables declaration//GEN-END:variables

}
