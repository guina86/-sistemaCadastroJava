/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import db.ClienteDao;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import modelo.Bairro;
import modelo.Cidade;
import modelo.Cliente;
import modelo.Estado;
import modelo.Telefone;
import utilitarios.ClienteTM;
import utilitarios.DefaultCM;

/**
 *
 * @author Leandro Guina
 */
public class DialogCliente extends javax.swing.JDialog {

    private final ClienteDao clienteDao;
    private final ClienteTM modeloTabela;
    private final DefaultCM<Bairro> modeloComboBairro;
    private final DefaultCM<Cidade> modeloComboCidade;
    private final DefaultCM<Estado> modeloComboEstado;
    private final DefaultCM<Telefone> modeloComboTelefone;
    private static final int QWERY = 0;
    private static final int INSERT = 1;
    private static final int UPDATE = 2;
    private int current;
    private int modo;

    /**
     * Creates new form DialogCliente
     */
    public DialogCliente(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        modeloComboBairro = new DefaultCM<>();
        modeloComboCidade = new DefaultCM<>();
        modeloComboEstado = new DefaultCM<>();
        modeloComboTelefone = new DefaultCM<>();
        clienteDao = new ClienteDao();
        modeloTabela = new ClienteTM(clienteDao.getAll());
        initComponents();
        inicializa();
    }

    private void inicializa() {
        //variavel global
        current = 0;
        modo = QWERY;

        //combobox
        comboBairro.setModel(modeloComboBairro);
        comboCidade.setModel(modeloComboCidade);
        comboEstado.setModel(modeloComboEstado);
        comboTelefone.setModel(modeloComboTelefone);
        comboEstado.addActionListener(e -> comboEstadoActionPerformed(e));
        comboCidade.addActionListener(e -> comboCidadeActionPerformed(e));

        //tabela
        tabelaCliente.getColumnModel().getColumn(0).setPreferredWidth(20);
        tabelaCliente.getColumnModel().getColumn(0).setResizable(false);
        tabelaCliente.getColumnModel().getColumn(1).setPreferredWidth(150);
        tabelaCliente.getColumnModel().getColumn(1).setResizable(false);
        tabelaCliente.getColumnModel().getColumn(2).setPreferredWidth(150);
        tabelaCliente.getColumnModel().getColumn(2).setResizable(false);
        tabelaCliente.getColumnModel().getColumn(3).setPreferredWidth(80);
        tabelaCliente.getColumnModel().getColumn(3).setResizable(false);
        tabelaCliente.getColumnModel().getColumn(4).setPreferredWidth(80);
        tabelaCliente.getColumnModel().getColumn(4).setResizable(false);
        tabelaCliente.getColumnModel().getColumn(5).setPreferredWidth(120);
        tabelaCliente.getColumnModel().getColumn(5).setResizable(false);
        tabelaCliente.getTableHeader().setReorderingAllowed(false);
        tabelaCliente.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelaCliente.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tabelaCliente.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                tabelaClienteMouseClicked();
            }
        });
    }

    private void setModo(int modo) {
        switch (modo) {
            case QWERY:
                this.modo = modo;
                setInterface(false, false, false, false, true, false, false, false, true, false, false, false, true, false, true, true, false, true, true, true);
                resetaCampos();
                break;
            case INSERT:
                this.modo = modo;
                setInterface(true, true, true, true, true, true, true, true, true, true, true, true, false, true, false, false, true, false, true, false);
                resetaCampos();
                break;
            case UPDATE:
                this.modo = modo;
                setInterface(true, true, true, true, true, true, true, true, true, true, true, true, false, true, false, false, true, false, true, false);
                preparaCombos();
                break;
            default:
                break;
        }
    }

    private void setCurrent(int current) {
        this.current = current;
        preencheCampos();
    }

    private void setInterface(boolean nome, boolean rg, boolean cpf, boolean endereco,
            boolean telefone, boolean estado, boolean cidade, boolean bairro, boolean novoTelefone,
            boolean novoEstado, boolean novoCidade, boolean novoBairro, boolean novo,
            boolean salva, boolean edita, boolean apaga, boolean cancela, boolean navegacao,
            boolean volta, boolean tabela) {

        campoNome.setEnabled(nome);
        campoRg.setEnabled(rg);
        campoCpf.setEnabled(cpf);
        campoEndereco.setEnabled(endereco);
        comboTelefone.setEnabled(telefone);
        comboEstado.setEnabled(estado);
        comboCidade.setEnabled(cidade);
        comboBairro.setEnabled(bairro);
        botaoNovoTelefone.setEnabled(novoTelefone);
        botaoNovoEstado.setEnabled(novoEstado);
        botaoNovoCidade.setEnabled(novoCidade);
        botaoNovoBairro.setEnabled(novoBairro);
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
        tabelaCliente.setRowSelectionAllowed(tabela);
        tabelaCliente.setFocusable(tabela);
    }

    private void resetaCampos() {
        campoNome.setText("");
        campoRg.setText("");
        campoCpf.setText("");
        campoCpf.setValue(null);
        campoEndereco.setText("");
        comboEstado.setSelectedItem(null);
        comboCidade.setSelectedItem(null);
        comboBairro.setSelectedItem(null);
        comboTelefone.setSelectedItem(null);
        modeloComboEstado.clear();
        modeloComboTelefone.clear();
        modeloComboEstado.addAll(clienteDao.estados());
    }

    private void preencheCampos() {
        Cliente cliente = modeloTabela.get(current);
        campoNome.setText(cliente.getNome());
        campoRg.setText(cliente.getRg());
        campoCpf.setText(cliente.getCpf());
        campoEndereco.setText(cliente.getEndereco());
        modeloComboEstado.clear();
        modeloComboEstado.add(cliente.getBairro().getCidade().getEstado());
        modeloComboCidade.clear();
        modeloComboCidade.add(cliente.getBairro().getCidade());
        modeloComboBairro.clear();
        modeloComboBairro.add(cliente.getBairro());
        modeloComboTelefone.clear();
        modeloComboTelefone.addAll(cliente.getTelefones());
        if (!modeloComboTelefone.isEmpty()) {
            comboTelefone.setSelectedIndex(0);
        }
    }

    private void preparaCombos() {
        Estado selectedEstado = modeloComboEstado.getSelectedItem();
        Cidade selectedCidade = modeloComboCidade.getSelectedItem();
        Bairro selectedBairro = modeloComboBairro.getSelectedItem();
        modeloComboEstado.clear();
        modeloComboEstado.addAll(clienteDao.estados());
        modeloComboEstado.setSelectedItem(selectedEstado);
        modeloComboCidade.clear();
        modeloComboCidade.addAll(clienteDao.cidades(selectedEstado));
        modeloComboCidade.setSelectedItem(selectedCidade);
        modeloComboBairro.clear();
        modeloComboBairro.addAll(clienteDao.bairros(selectedCidade));
        modeloComboBairro.setSelectedItem(selectedBairro);
    }

    private boolean validaCampos() {
        if (campoNome.getText().equals("") || campoEndereco.getText().equals("")
                || campoRg.getText().equals("") || campoCpf.getText().equals("")
                || comboEstado.getSelectedItem().equals("") || comboCidade.getSelectedItem().equals("")
                || comboBairro.getSelectedItem().equals("")) {
            JOptionPane.showMessageDialog(this, "Confira os dados selecionados, registro incompleto", "Resgisto incompleto", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    private Cliente colectaCampos() {
        int id = 0;
        if(!modeloTabela.isEmpty()){
            id = modeloTabela.get(current).getId(); 
        }
        return new Cliente(id, campoNome.getText(), campoEndereco.getText(), campoRg.getText(), campoCpf.getText(), modeloComboBairro.getSelectedItem(), modeloComboTelefone.getAll());
    }

//    private List<String> getComboTelefones() {
//        List<String> lista = new ArrayList<>();
//        int j = comboTelefone.getModel().getSize();
//        for (int i = 0; i < j; i++) {
//            String itemAt = comboTelefone.getItemAt(i);
//            if (!itemAt.equals("")) {
//                lista.add(comboTelefone.getItemAt(i));
//            }
//        }
//        return lista;
//    }

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
        campoNome = new javax.swing.JTextField();
        labelTel = new javax.swing.JLabel();
        comboTelefone = new javax.swing.JComboBox<>();
        botaoNovoTelefone = new javax.swing.JButton();
        labelEndereco = new javax.swing.JLabel();
        campoEndereco = new javax.swing.JTextField();
        labelRg = new javax.swing.JLabel();
        campoRg = new javax.swing.JTextField();
        labelCpf = new javax.swing.JLabel();
        campoCpf = new javax.swing.JFormattedTextField();
        labelBairro = new javax.swing.JLabel();
        comboBairro = new javax.swing.JComboBox<>();
        botaoNovoBairro = new javax.swing.JButton();
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
        tabelaCliente = new javax.swing.JTable();
        labelCidade = new javax.swing.JLabel();
        comboCidade = new javax.swing.JComboBox<>();
        botaoNovoCidade = new javax.swing.JButton();
        labelEstado = new javax.swing.JLabel();
        comboEstado = new javax.swing.JComboBox<>();
        botaoNovoEstado = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cadastro de Clientes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        labelNome.setText("Nome:");

        campoNome.setEnabled(false);

        labelTel.setText("Tel:");

        comboTelefone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTelefoneActionPerformed(evt);
            }
        });

        botaoNovoTelefone.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/addSmall.png"))); // NOI18N
        botaoNovoTelefone.setToolTipText("Novo Telefone");
        botaoNovoTelefone.setEnabled(false);
        botaoNovoTelefone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoNovoTelefoneActionPerformed(evt);
            }
        });

        labelEndereco.setText("Endereço:");

        campoEndereco.setEnabled(false);

        labelRg.setText("RG:");

        campoRg.setEnabled(false);

        labelCpf.setText("CPF:");

        try {
            campoCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        campoCpf.setEnabled(false);

        labelBairro.setText("Bairro:");

        comboBairro.setEnabled(false);

        botaoNovoBairro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/addSmall.png"))); // NOI18N
        botaoNovoBairro.setToolTipText("Novo Bairro");
        botaoNovoBairro.setEnabled(false);
        botaoNovoBairro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoNovoBairroActionPerformed(evt);
            }
        });

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

        tabelaCliente.setModel(modeloTabela);
        jScrollPane1.setViewportView(tabelaCliente);

        labelCidade.setText("Cidade:");

        comboCidade.setEnabled(false);

        botaoNovoCidade.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/addSmall.png"))); // NOI18N
        botaoNovoCidade.setToolTipText("Nova Cidade");
        botaoNovoCidade.setEnabled(false);
        botaoNovoCidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoNovoCidadeActionPerformed(evt);
            }
        });

        labelEstado.setText("Estado:");

        comboEstado.setActionCommand("comboEstado");
        comboEstado.setEnabled(false);

        botaoNovoEstado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/addSmall.png"))); // NOI18N
        botaoNovoEstado.setToolTipText("Novo Estado");
        botaoNovoEstado.setEnabled(false);
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(labelNome)
                                .addComponent(labelEndereco))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(campoNome, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                                .addComponent(campoEndereco))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(labelRg, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(labelEstado, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(comboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(botaoNovoEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(campoRg))
                            .addGap(11, 11, 11)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(labelCpf, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(labelCidade, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(comboCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(botaoNovoCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(campoCpf))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(labelTel, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(labelBairro, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(comboTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(botaoNovoTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(comboBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(botaoNovoBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(163, 163, 163)
                            .addComponent(botaoNovo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(botaoSalva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(botaoEdita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(botaoApaga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(botaoCancela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(27, 27, 27)
                            .addComponent(botaoPrimeiro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(botaoAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(botaoProximo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(botaoUltimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(24, 24, 24)
                            .addComponent(botaoVolta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 10, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelNome)
                    .addComponent(campoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelRg)
                    .addComponent(campoRg, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCpf)
                    .addComponent(campoCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelTel)
                    .addComponent(comboTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoNovoTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelEndereco)
                    .addComponent(campoEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelEstado)
                    .addComponent(comboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoNovoEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCidade)
                    .addComponent(comboCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoNovoCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelBairro)
                    .addComponent(comboBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoNovoBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
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
            Cliente cliente = colectaCampos();
            if (modo == INSERT) {
                int id = clienteDao.save(cliente);
                cliente.setId(id);
                cliente.setTelefones(clienteDao.telefones(cliente));
                modeloTabela.add(cliente);
            } else if (modo == UPDATE) {
                clienteDao.update(cliente);
                modeloTabela.setValueAt(cliente, current);
            }
            setModo(QWERY);
        }
    }//GEN-LAST:event_botaoSalvaActionPerformed

    private void botaoEditaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoEditaActionPerformed
        if (tabelaCliente.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Nenhum registro selecionado", "Opção inválida", JOptionPane.WARNING_MESSAGE);
        } else {
            setModo(UPDATE);
        }
    }//GEN-LAST:event_botaoEditaActionPerformed

    private void botaoApagaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoApagaActionPerformed
        if (tabelaCliente.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Nenhum registro selecionado", "Opção inválida", JOptionPane.WARNING_MESSAGE);
        } else {
            int opcao = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja apagar " + campoNome.getText() + " ?",
                    "Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (opcao == JOptionPane.YES_OPTION) {
                clienteDao.delete(modeloTabela.get(current));
                modeloTabela.remove(current);
            }
        }
    }//GEN-LAST:event_botaoApagaActionPerformed

    private void botaoCancelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelaActionPerformed
        setModo(QWERY);
        tabelaCliente.clearSelection();
    }//GEN-LAST:event_botaoCancelaActionPerformed

    private void botaoPrimeiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoPrimeiroActionPerformed
        setCurrent(0);
        tabelaCliente.setRowSelectionInterval(current, current);
    }//GEN-LAST:event_botaoPrimeiroActionPerformed

    private void botaoAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAnteriorActionPerformed
        if (current > 0) {
            setCurrent(current - 1);
        }
        tabelaCliente.setRowSelectionInterval(current, current);
    }//GEN-LAST:event_botaoAnteriorActionPerformed

    private void botaoProximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoProximoActionPerformed
        int size = modeloTabela.getRowCount();
        if (tabelaCliente.getSelectedRow() == -1) {
            setCurrent(0);
        } else if (size > 1 && current < size - 1) {
            setCurrent(current + 1);
        }
        tabelaCliente.setRowSelectionInterval(current, current);
    }//GEN-LAST:event_botaoProximoActionPerformed

    private void botaoUltimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoUltimoActionPerformed
        setCurrent(modeloTabela.getRowCount() - 1);
        tabelaCliente.setRowSelectionInterval(current, current);
    }//GEN-LAST:event_botaoUltimoActionPerformed

    private void botaoVoltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoVoltaActionPerformed
        this.dispose();
    }//GEN-LAST:event_botaoVoltaActionPerformed

    private void botaoNovoTelefoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoNovoTelefoneActionPerformed
        DialogQuickTelefone dialog = new DialogQuickTelefone((Frame) getParent(), true);
        String nome = campoNome.getText().equals("") ? "Novo Cliente" : campoNome.getText();
        List<Telefone> telefones = dialog.editTelefone(modeloComboTelefone.getAll(), nome);
        modeloComboTelefone.clear();
        modeloComboTelefone.addAll(telefones);
    }//GEN-LAST:event_botaoNovoTelefoneActionPerformed

    private void comboTelefoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTelefoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboTelefoneActionPerformed

    private void botaoNovoEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoNovoEstadoActionPerformed
        Estado estado = new DialogEstado((Frame) getParent(), true).edtitEstado();
        if (estado != null && !modeloComboEstado.getAll().contains(estado)) {
            modeloComboEstado.add(estado);
        }
        comboEstado.setSelectedItem(estado);
        comboEstado.repaint();
    }//GEN-LAST:event_botaoNovoEstadoActionPerformed

    private void botaoNovoCidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoNovoCidadeActionPerformed
        Cidade cidade = new DialogCidade((Frame) getParent(), true).edtitCidade();
        if (cidade != null && !modeloComboCidade.getAll().contains(cidade)) {
            modeloComboCidade.add(cidade);
        }
        comboCidade.setSelectedItem(cidade);
        comboCidade.repaint();
    }//GEN-LAST:event_botaoNovoCidadeActionPerformed

    private void botaoNovoBairroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoNovoBairroActionPerformed
        String bairro = new DialogBairro((Frame)getParent(), true).edtitBairro();
        comboBairro.addItem(bairro);
        comboBairro.setSelectedItem(bairro);
    }//GEN-LAST:event_botaoNovoBairroActionPerformed

    private void tabelaClienteMouseClicked() {
        if (modo == QWERY) {
            setCurrent(tabelaCliente.getSelectedRow());
        }
    }

    private void comboEstadoActionPerformed(java.awt.event.ActionEvent e) {
        if (modeloComboEstado.getSelectedItem() != null) {
            modeloComboCidade.clear();
            modeloComboCidade.addAll(clienteDao.cidades(modeloComboEstado.getSelectedItem()));
            modeloComboCidade.setSelectedItem(null);
        }
    }

    private void comboCidadeActionPerformed(java.awt.event.ActionEvent e) {
        if (modeloComboCidade.getSelectedItem() != null) {
            modeloComboBairro.clear();
            modeloComboBairro.setSelectedItem(null);
            modeloComboBairro.addAll(clienteDao.bairros(modeloComboCidade.getSelectedItem()));
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
            java.util.logging.Logger.getLogger(DialogCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DialogCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DialogCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DialogCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DialogCliente dialog = new DialogCliente(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton botaoNovoBairro;
    private javax.swing.JButton botaoNovoCidade;
    private javax.swing.JButton botaoNovoEstado;
    private javax.swing.JButton botaoNovoTelefone;
    private javax.swing.JButton botaoPrimeiro;
    private javax.swing.JButton botaoProximo;
    private javax.swing.JButton botaoSalva;
    private javax.swing.JButton botaoUltimo;
    private javax.swing.JButton botaoVolta;
    private javax.swing.JFormattedTextField campoCpf;
    private javax.swing.JTextField campoEndereco;
    private javax.swing.JTextField campoNome;
    private javax.swing.JTextField campoRg;
    private javax.swing.JComboBox<String> comboBairro;
    private javax.swing.JComboBox<String> comboCidade;
    private javax.swing.JComboBox<String> comboEstado;
    private javax.swing.JComboBox<String> comboTelefone;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelBairro;
    private javax.swing.JLabel labelCidade;
    private javax.swing.JLabel labelCpf;
    private javax.swing.JLabel labelEndereco;
    private javax.swing.JLabel labelEstado;
    private javax.swing.JLabel labelNome;
    private javax.swing.JLabel labelRg;
    private javax.swing.JLabel labelTel;
    private javax.swing.JTable tabelaCliente;
    // End of variables declaration//GEN-END:variables

}
