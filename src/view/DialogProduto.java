/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import db.FornecedorDao;
import db.ProdutoDao;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableModel;
import modelo.Fornecedor;
import modelo.Produto;
import utilitarios.DefaultCM;
import utilitarios.ProdutoTM;

/**
 *
 * @author Leandro Guina
 */
public class DialogProduto extends javax.swing.JDialog {

    private final FornecedorDao fornecedorDao;
    private final ProdutoDao produtoDao;
    private final ProdutoTM modeloTabela;
    private final DefaultCM<Fornecedor> modeloComboFornecedor;
    private static final int QWERY = 0;
    private static final int INSERT = 1;
    private static final int UPDATE = 2;
    private int current;
    private int modo;

    /**
     * Creates new form DialogCliente
     */
    public DialogProduto(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        modeloComboFornecedor = new DefaultCM<>();
        produtoDao = new ProdutoDao();
        fornecedorDao = new FornecedorDao();
        modeloTabela = new ProdutoTM();
        initComponents();
        inicializa();
    }

    private void inicializa() {
        //variavel global
        current = 0;
        modo = QWERY;

        //combobox
        comboFornecedor.setModel(modeloComboFornecedor);
        modeloComboFornecedor.addAll(fornecedorDao.getAll());

        //tabela
        tabelaProduto.getColumnModel().getColumn(0).setPreferredWidth(20);
        tabelaProduto.getColumnModel().getColumn(0).setResizable(false);
        tabelaProduto.getColumnModel().getColumn(1).setPreferredWidth(150);
        tabelaProduto.getColumnModel().getColumn(1).setResizable(false);
        tabelaProduto.getColumnModel().getColumn(2).setPreferredWidth(150);
        tabelaProduto.getColumnModel().getColumn(2).setResizable(false);
        tabelaProduto.getColumnModel().getColumn(3).setPreferredWidth(80);
        tabelaProduto.getColumnModel().getColumn(3).setResizable(false);
        tabelaProduto.getColumnModel().getColumn(4).setPreferredWidth(80);
        tabelaProduto.getColumnModel().getColumn(4).setResizable(false);
        tabelaProduto.getColumnModel().getColumn(5).setPreferredWidth(120);
        tabelaProduto.getColumnModel().getColumn(5).setResizable(false);
        tabelaProduto.getTableHeader().setReorderingAllowed(false);
        tabelaProduto.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelaProduto.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tabelaProduto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                tabelaClienteMouseClicked();
            }
        });
        modeloTabela.addLista(produtoDao.getAll());
    }

    private void setModo(int modo) {
        switch (modo) {
            case QWERY:
                this.modo = modo;
                setInterface(false, false, false, false, false, true, true, false, true, true, false, true, true, true);
                resetaCampos();
                break;
            case INSERT:
                this.modo = modo;
                setInterface(true, true, true, true, true, false, false, true, false, false, true, false, true, false);
                resetaCampos();
                break;
            case UPDATE:
                this.modo = modo;
                setInterface(true, true, true, true, true, false, false, true, false, false, true, false, true, false);
//                preparaCombos();
                break;
            default:
                break;
        }
    }

    private void setCurrent(int current) {
        this.current = current;
        preencheCampos();
    }

    private void setInterface(boolean nome, boolean quantidade, boolean compra, boolean venda,
            boolean fornecedor, boolean pesquisa, boolean novo,
            boolean salva, boolean edita, boolean apaga, boolean cancela, boolean navegacao,
            boolean volta, boolean tabela) {

        campoProduto.setEnabled(nome);
        campoQuantidade.setEnabled(quantidade);
        campoPrecoCompra.setEnabled(compra);
        campoPrecoVenda.setEnabled(venda);
        comboFornecedor.setEnabled(fornecedor);
        campoPesquisa.setEnabled(pesquisa);
        botaoPesquisa.setEnabled(pesquisa);
        botaoNovo.setEnabled(novo);
        botaoSalva.setEnabled(salva);
        botaoEdita.setEnabled(edita);
        botaoApaga.setEnabled(apaga);
        botaoCancela.setEnabled(cancela);
        botaoPrimeiro.setEnabled(navegacao);
        botaoAnterior.setEnabled(navegacao);
        botaoProximo.setEnabled(navegacao);
        botaoUltimo.setEnabled(navegacao);
        botaoVolta.setEnabled(volta);
        tabelaProduto.setRowSelectionAllowed(tabela);
        tabelaProduto.setFocusable(tabela);
    }

    private void resetaCampos() {
        campoCodigo.setText("");
        campoProduto.setText("");
        campoQuantidade.setText("");
        campoPrecoCompra.setText("");
        campoPrecoVenda.setText("");
        campoPesquisa.setText("");
        comboFornecedor.setSelectedItem(null);
        comboFornecedor.repaint();
    }

    private void preencheCampos() {
        Produto produto = modeloTabela.get(current);
        campoCodigo.setText(produto.getId().toString());
        campoProduto.setText(produto.getNome());
        campoQuantidade.setText(produto.getQuantidade().toString());
        campoPrecoCompra.setText(produto.getPrecoCompra().toString());
        campoPrecoVenda.setText(produto.getPrecoVenda().toString());
        if (!modeloComboFornecedor.isEmpty()) {
            modeloComboFornecedor.setSelectedItem(produto.getFornecedor());
            comboFornecedor.repaint();
        }
    }


    private boolean validaCampos() {
        if (campoProduto.getText().equals("") || campoQuantidade.getText().equals("")
                || campoPrecoCompra.getText().equals("") || campoPrecoVenda.getText().equals("")
                || comboFornecedor.getSelectedItem().equals("")) {
            JOptionPane.showMessageDialog(this, "Confira os dados selecionados, registro incompleto", "Resgistro incompleto", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    private Produto colectaCampos() {
        int id = 0;
        if (!modeloTabela.isEmpty()) {
            id = modeloTabela.get(current).getId();
        }
        return new Produto(id, campoProduto.getText(), Double.valueOf(campoPrecoCompra.getText()), Double.valueOf(campoPrecoVenda.getText()), Integer.valueOf(campoQuantidade.getText()), modeloComboFornecedor.getSelectedItem());
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
        labelCodigo = new javax.swing.JLabel();
        campoCodigo = new javax.swing.JTextField();
        labelPrecoCompra = new javax.swing.JLabel();
        campoPrecoCompra = new javax.swing.JTextField();
        labelProduto = new javax.swing.JLabel();
        campoProduto = new javax.swing.JTextField();
        labelQuantidade = new javax.swing.JLabel();
        labelFornecedor = new javax.swing.JLabel();
        comboFornecedor = new javax.swing.JComboBox<>();
        botaoNovo = new javax.swing.JButton();
        botaoSalva = new javax.swing.JButton();
        botaoEdita = new javax.swing.JButton();
        botaoApaga = new javax.swing.JButton();
        botaoCancela = new javax.swing.JButton();
        botaoVolta = new javax.swing.JButton();
        botaoPrimeiro = new javax.swing.JButton();
        botaoAnterior = new javax.swing.JButton();
        botaoProximo = new javax.swing.JButton();
        botaoUltimo = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaProduto = new javax.swing.JTable();
        campoPesquisa = new javax.swing.JTextField();
        botaoPesquisa = new javax.swing.JButton();
        labelPrecoVenda = new javax.swing.JLabel();
        campoPrecoVenda = new javax.swing.JTextField();
        campoQuantidade = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cadastro de Produtos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        labelCodigo.setText("Código:");

        campoCodigo.setEnabled(false);

        labelPrecoCompra.setText("Ppreço de Compra:");

        campoPrecoCompra.setEnabled(false);

        labelProduto.setText("Produto:");

        campoProduto.setEnabled(false);

        labelQuantidade.setText("Quantidade:");

        labelFornecedor.setText("Fornecedor:");

        comboFornecedor.setEnabled(false);

        botaoNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/add_36p.png"))); // NOI18N
        botaoNovo.setToolTipText("Novo");
        botaoNovo.setActionCommand("botaoNovo");
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
        botaoUltimo.setToolTipText("Último");
        botaoUltimo.setMaximumSize(new java.awt.Dimension(50, 50));
        botaoUltimo.setMinimumSize(new java.awt.Dimension(50, 50));
        botaoUltimo.setPreferredSize(new java.awt.Dimension(50, 50));
        botaoUltimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoUltimoActionPerformed(evt);
            }
        });

        tabelaProduto.setModel(modeloTabela);
        jScrollPane1.setViewportView(tabelaProduto);

        botaoPesquisa.setText("Pesquisar");
        botaoPesquisa.setToolTipText("Pesquisar");
        botaoPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoPesquisaActionPerformed(evt);
            }
        });

        labelPrecoVenda.setText("Preço de Venda:");

        campoPrecoVenda.setEnabled(false);

        campoQuantidade.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(botaoPesquisa, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                            .addComponent(campoPesquisa))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botaoPrimeiro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botaoAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botaoProximo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botaoUltimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botaoVolta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(labelCodigo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(labelProduto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(labelPrecoCompra)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoPrecoCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(labelPrecoVenda)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoPrecoVenda)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(labelQuantidade))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(labelFornecedor)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(comboFornecedor, 0, 180, Short.MAX_VALUE)
                            .addComponent(campoQuantidade))))
                .addGap(0, 10, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelCodigo)
                    .addComponent(campoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelProduto)
                    .addComponent(campoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelQuantidade)
                    .addComponent(campoQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(comboFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelFornecedor)
                    .addComponent(campoPrecoVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelPrecoVenda)
                    .addComponent(campoPrecoCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelPrecoCompra))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(botaoNovo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoSalva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoEdita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoApaga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoCancela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoPrimeiro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoProximo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoUltimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoVolta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(campoPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoPesquisa)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void botaoNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoNovoActionPerformed
        setModo(INSERT);
    }//GEN-LAST:event_botaoNovoActionPerformed

    private void botaoSalvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSalvaActionPerformed
        if (validaCampos()) {
            Produto produto = colectaCampos();
            if (modo == INSERT) {
                int id = produtoDao.save(produto);
                produto.setId(id);
                modeloTabela.add(produto);
            } else if (modo == UPDATE) {
                produtoDao.update(produto);
                modeloTabela.setValueAt(produto, current);
            }
            setModo(QWERY);
        }
    }//GEN-LAST:event_botaoSalvaActionPerformed

    private void botaoEditaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoEditaActionPerformed
        if (tabelaProduto.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Nenhum registro selecionado", "Opção inválida", JOptionPane.WARNING_MESSAGE);
        } else {
            setModo(UPDATE);
        }
    }//GEN-LAST:event_botaoEditaActionPerformed

    private void botaoApagaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoApagaActionPerformed
        if (tabelaProduto.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Nenhum registro selecionado", "Opção inválida", JOptionPane.WARNING_MESSAGE);
        } else {
            int opcao = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja apagar " + campoCodigo.getText() + " ?",
                    "Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (opcao == JOptionPane.YES_OPTION) {
                produtoDao.delete(modeloTabela.get(current));
                modeloTabela.remove(current);
                resetaCampos();
            }
        }
    }//GEN-LAST:event_botaoApagaActionPerformed

    private void botaoCancelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelaActionPerformed
        setModo(QWERY);
        tabelaProduto.clearSelection();
    }//GEN-LAST:event_botaoCancelaActionPerformed

    private void botaoPrimeiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoPrimeiroActionPerformed
        setCurrent(0);
        tabelaProduto.setRowSelectionInterval(current, current);
    }//GEN-LAST:event_botaoPrimeiroActionPerformed

    private void botaoAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAnteriorActionPerformed
        if (current > 0) {
            setCurrent(current - 1);
        }
        tabelaProduto.setRowSelectionInterval(current, current);
    }//GEN-LAST:event_botaoAnteriorActionPerformed

    private void botaoProximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoProximoActionPerformed
        int size = modeloTabela.getRowCount();
        if (tabelaProduto.getSelectedRow() == -1) {
            setCurrent(0);
        } else if (size > 1 && current < size - 1) {
            setCurrent(current + 1);
        }
        tabelaProduto.setRowSelectionInterval(current, current);
    }//GEN-LAST:event_botaoProximoActionPerformed

    private void botaoUltimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoUltimoActionPerformed
        setCurrent(modeloTabela.getRowCount() - 1);
        tabelaProduto.setRowSelectionInterval(current, current);
    }//GEN-LAST:event_botaoUltimoActionPerformed

    private void botaoVoltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoVoltaActionPerformed
        this.dispose();
    }//GEN-LAST:event_botaoVoltaActionPerformed

    private void botaoPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoPesquisaActionPerformed
        Produto produto = produtoDao.find(campoPesquisa.getText());
        int indice = modeloTabela.indexOf(produto);
        tabelaProduto.setRowSelectionInterval(indice, indice);
        setCurrent(indice);
    }//GEN-LAST:event_botaoPesquisaActionPerformed

    private void tabelaClienteMouseClicked() {
        if (modo == QWERY) {
            setCurrent(tabelaProduto.getSelectedRow());
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
            java.util.logging.Logger.getLogger(DialogProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DialogProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DialogProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DialogProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DialogProduto dialog = new DialogProduto(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton botaoPesquisa;
    private javax.swing.JButton botaoPrimeiro;
    private javax.swing.JButton botaoProximo;
    private javax.swing.JButton botaoSalva;
    private javax.swing.JButton botaoUltimo;
    private javax.swing.JButton botaoVolta;
    private javax.swing.JTextField campoCodigo;
    private javax.swing.JTextField campoPesquisa;
    private javax.swing.JTextField campoPrecoCompra;
    private javax.swing.JTextField campoPrecoVenda;
    private javax.swing.JTextField campoProduto;
    private javax.swing.JTextField campoQuantidade;
    private javax.swing.JComboBox<String> comboFornecedor;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelCodigo;
    private javax.swing.JLabel labelFornecedor;
    private javax.swing.JLabel labelPrecoCompra;
    private javax.swing.JLabel labelPrecoVenda;
    private javax.swing.JLabel labelProduto;
    private javax.swing.JLabel labelQuantidade;
    private javax.swing.JTable tabelaProduto;
    // End of variables declaration//GEN-END:variables

}
