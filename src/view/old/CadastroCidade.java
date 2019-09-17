/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.old;

import db.CidadeDao;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import modelo.Cidade;
import utilitarios.CidadeTM;
import view.componentes.PainelGBL;

/**
 *
 * @author Leandro Guina
 */
public class CadastroCidade extends JDialog {

    private static CadastroCidade instance = null;
    private final CidadeDao cidadeDao;
    private int current;
    private String modoEdicao;
    private final CidadeTM modelo;
    // componentes Swing
    private JButton botaoAnterior;
    private JButton botaoApaga;
    private JButton botaoCancela;
    private JButton botaoEdita;
    private JButton botaoNovo;
    private JButton botaoPrimeiro;
    private JButton botaoProximo;
    private JButton botaoSalva;
    private JButton botaoUltimo;
    private JButton botaoVolta;
    private JButton botaoNovoEstado;
    private JTextField campoId;
    private JTextField campoNome;
    private JComboBox comboEstado;
    private JScrollPane scrollTabela;
    private JLabel labelId;
    private JLabel labelNome;
    private JLabel labelEstado;
    private JTable tabelaCidade;
    private PainelGBL painel;

    private CadastroCidade() {
        cidadeDao = new CidadeDao();
        modelo = new CidadeTM(cidadeDao.getAll());
        current = 0;
        modoEdicao = "";
        inicializa();
    }
    
    public static CadastroCidade getInstance(){
        if(instance == null){
            instance = new CadastroCidade();
        }
        return instance;
    }
    
    private void inicializa() {
        labelId = new JLabel("Id:", JLabel.RIGHT);
        labelNome = new JLabel("Nome:", JLabel.RIGHT);
        labelEstado = new JLabel("Estado:", JLabel.RIGHT);
        campoId = new JTextField();
        campoNome = new JTextField();
        comboEstado = new JComboBox();
        botaoNovo = new JButton(new ImageIcon(getClass().getResource("/imagens/add_48p.png")));
        botaoSalva = new JButton(new ImageIcon(getClass().getResource("/imagens/save_48p.png")));
        botaoEdita = new JButton(new ImageIcon(getClass().getResource("/imagens/edit_48p.png")));
        botaoApaga = new JButton(new ImageIcon(getClass().getResource("/imagens/delete_48p.png")));
        botaoVolta = new JButton(new ImageIcon(getClass().getResource("/imagens/exit_48p.png")));
        botaoPrimeiro = new JButton(new ImageIcon(getClass().getResource("/imagens/first_48p.png")));
        botaoAnterior = new JButton(new ImageIcon(getClass().getResource("/imagens/left_48p.png")));
        botaoProximo = new JButton(new ImageIcon(getClass().getResource("/imagens/right_48p.png")));
        botaoCancela = new JButton(new ImageIcon(getClass().getResource("/imagens/cancel_48p.png")));
        botaoUltimo = new JButton(new ImageIcon(getClass().getResource("/imagens/last_48p.png")));
        botaoNovoEstado = new JButton(new ImageIcon(getClass().getResource("/imagens/add_24p.png")));
        tabelaCidade = new JTable(modelo);
        scrollTabela = new JScrollPane();
        painel = new PainelGBL();

        campoId.setEnabled(false);


        CompoundBorder compBorder = BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8),
                BorderFactory.createTitledBorder(null, "Cadastro de cidades", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.PLAIN, 18), Color.GRAY));
        painel.setBorder(BorderFactory.createCompoundBorder(compBorder, BorderFactory.createEmptyBorder(4, 4, 4, 4)));
         
        botaoNovo.setToolTipText("Novo");
        botaoNovo.setPreferredSize(new Dimension(60,60));
        botaoNovo.addActionListener(e -> botaoNovoActionPerformed(e));

        botaoSalva.setToolTipText("Salvar");
        botaoSalva.setPreferredSize(new Dimension(60,60));
        botaoSalva.addActionListener(e -> botaoSalvaActionPerformed(e));

        botaoEdita.setToolTipText("Editar");
        botaoEdita.setPreferredSize(new Dimension(60,60));
        botaoEdita.addActionListener(e -> botaoEditaActionPerformed(e));

        botaoApaga.setToolTipText("Apagar");
        botaoApaga.setPreferredSize(new Dimension(60,60));
        botaoApaga.addActionListener(e -> botaoApagaActionPerformed(e));

        botaoVolta.setToolTipText("Voltar");
        botaoVolta.setPreferredSize(new Dimension(60,60));
        botaoVolta.addActionListener(e -> botaoVoltaActionPerformed(e));

        botaoPrimeiro.setToolTipText("Primeiro");
        botaoPrimeiro.setPreferredSize(new Dimension(60,60));
        botaoPrimeiro.addActionListener(e -> botaoPrimeiroActionPerformed(e));

        botaoProximo.setToolTipText("Próximo");
        botaoProximo.setPreferredSize(new Dimension(60,60));
        botaoProximo.addActionListener(e -> botaoProximoActionPerformed(e));

        botaoUltimo.setToolTipText("Ultimo");
        botaoUltimo.setPreferredSize(new Dimension(60,60));
        botaoUltimo.addActionListener(e -> botaoUltimoActionPerformed(e));

        botaoAnterior.setToolTipText("Anterior");
        botaoAnterior.setPreferredSize(new Dimension(60,60));
        botaoAnterior.addActionListener(e -> botaoAnteriorActionPerformed(e));

        botaoCancela.setToolTipText("Voltar");
        botaoCancela.setPreferredSize(new Dimension(60,60));
        botaoCancela.addActionListener(e -> botaoCancelaActionPerformed(e));
        
        botaoNovoEstado.setToolTipText("Novo estado");
        botaoNovoEstado.setPreferredSize(new Dimension(20,20));
        botaoNovoEstado.addActionListener(e -> botaoNovoEstadoActionPerformed(e));

        comboEstado.setPreferredSize(new Dimension(100,20));
        comboEstado.removeAllItems();
        preencherComboCidade();
        
        tabelaCidade.getColumnModel().getColumn(0).setPreferredWidth(20);
        tabelaCidade.getColumnModel().getColumn(0).setResizable(false);
        tabelaCidade.getColumnModel().getColumn(1).setPreferredWidth(150);
        tabelaCidade.getColumnModel().getColumn(1).setResizable(false);
        tabelaCidade.getColumnModel().getColumn(2).setPreferredWidth(150);
        tabelaCidade.getColumnModel().getColumn(2).setResizable(false);
        tabelaCidade.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelaCidade.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tabelaCidade.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tabelaCidadeMouseClicked(e);
            }
        });
        scrollTabela.setViewportView(tabelaCidade);
        scrollTabela.setPreferredSize(new Dimension(200, 250));

        painel.adiciona(labelId, 0, 0);
        painel.adiciona(campoId, 1, 0);
        painel.adiciona(botaoNovoEstado, 5, 0);
        painel.adiciona(labelNome, 0, 1);
        painel.adiciona(campoNome, 1, 1, 2, 1);
        painel.adiciona(labelEstado, 3, 1);
        painel.adiciona(comboEstado, 4, 1, 2, 1);
        painel.adiciona(botaoNovo, 0, 2);
        painel.adiciona(botaoSalva, 1, 2);
        painel.adiciona(botaoEdita, 2, 2);
        painel.adiciona(botaoApaga, 3, 2);
        painel.adiciona(botaoCancela, 4, 2);
        painel.adiciona(botaoVolta, 5, 2);
        painel.adiciona(scrollTabela, 0, 3, 6, 6);
        painel.adiciona(botaoPrimeiro, 0, 9);
        painel.adiciona(botaoAnterior, 1, 9);
        painel.adiciona(botaoProximo, 2, 9);
        painel.adiciona(botaoUltimo, 3, 9);

        add(painel);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de cidade");
        setResizable(false);
        setInterface(false, false, true, false, false, false, true, false, true, true);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }

    private void setInterface(boolean nome, boolean cidade, boolean novo, boolean salva, boolean edita, boolean apaga, boolean navegacao, boolean cancela, boolean tabela, boolean limpaCampos) {
        limpaCampos(limpaCampos);
        campoNome.setEnabled(nome);
        comboEstado.setEnabled(cidade);
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

    private void limpaCampos(boolean opcao) {
        if (opcao) {
            campoId.setText("");
            campoNome.setText("");
            comboEstado.setSelectedIndex(0);
            tabelaCidade.clearSelection();
        }
    }

    private void preencherCampos(Cidade cidade) {
        campoId.setText(cidade.getId().toString());
        campoNome.setText(cidade.getNome());
        comboEstado.setSelectedItem(cidade.getEstado());
    }

    private void preencherComboCidade(){
        comboEstado.removeAllItems();
        comboEstado.addItem("");
        cidadeDao.estados().forEach(s-> comboEstado.addItem(s));
    }
    
    private void botaoNovoActionPerformed(java.awt.event.ActionEvent evt) {
        modoEdicao = "salvar";
        tabelaCidade.setRowSelectionAllowed(false);
        setInterface(true, true, false, true, false, false, false, true, false, true);
        campoNome.requestFocus();
    }

    private void botaoSalvaActionPerformed(java.awt.event.ActionEvent evt) {
        if (modoEdicao.equals("salvar")) {
            Cidade cidade = new Cidade(campoNome.getText(), comboEstado.getSelectedItem().toString());
            int id = cidadeDao.save(cidade);
            cidade.setId(id);
            modelo.add(cidade);
        } else if (modoEdicao.equals("update")) {
            Cidade cidade = new Cidade(Integer.valueOf(campoId.getText()), campoNome.getText(), comboEstado.getSelectedItem().toString());
            cidadeDao.update(cidade);
            modelo.setValueAt(cidade, current);
            System.out.println(modelo.get(current));
        }
        setInterface(false, false, true, false, false, false, true, false, true, true);
    }

    private void botaoEditaActionPerformed(java.awt.event.ActionEvent evt) {
        modoEdicao = "update";
        setInterface(true, true, false, true, false, false, false, true, true, false);
        campoNome.requestFocus();
    }

    private void botaoApagaActionPerformed(java.awt.event.ActionEvent evt) {
        int option = JOptionPane.showConfirmDialog(this, "Tem certeza que quer excluir " + campoNome.getText(), "Confirmação", JOptionPane.YES_NO_OPTION);
        if (option == 0) {
            int id = Integer.parseInt(campoId.getText());
            cidadeDao.delete(id);
            modelo.remove(current);
        }
        setInterface(false, false, true, false, false, false, true, false, true, true);
    }

    private void botaoVoltaActionPerformed(java.awt.event.ActionEvent evt) {
       dispose();
    }

    private void tabelaCidadeMouseClicked(java.awt.event.MouseEvent evt) {
        current = tabelaCidade.getSelectedRow();
        Cidade cidade = modelo.get(current);
        preencherCampos(cidade);
        setInterface(false, false, true, false, true, true, true, false, true, false);
    }

    private void botaoPrimeiroActionPerformed(java.awt.event.ActionEvent evt) {
        current = 0;
        tabelaCidade.setRowSelectionInterval(current, current);
        Cidade cidade = modelo.get(0);
        preencherCampos(cidade);
        setInterface(false, false, true, false, true, true, true, false, true, false);
    }

    private void botaoProximoActionPerformed(java.awt.event.ActionEvent evt) {
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
    }

    private void botaoUltimoActionPerformed(java.awt.event.ActionEvent evt) {
        current = modelo.getRowCount() - 1;
        tabelaCidade.setRowSelectionInterval(current, current);
        Cidade cidade = modelo.get(current);
        preencherCampos(cidade);
        setInterface(false, false, true, false, true, true, true, false, true, false);
    }

    private void botaoAnteriorActionPerformed(java.awt.event.ActionEvent evt) {
        if (current > 0) {
            current--;
        }
        tabelaCidade.setRowSelectionInterval(current, current);
        Cidade cidade = modelo.get(current);
        preencherCampos(cidade);
        setInterface(false, false, true, false, true, true, true, false, true, false);
    }

    private void botaoCancelaActionPerformed(java.awt.event.ActionEvent evt) {
        setInterface(false, false, true, false, false, false, true, false, true, true);
    }

    private void botaoNovoEstadoActionPerformed(ActionEvent e) {
        CadastroEstado cadastroEstado = CadastroEstado.getInstance();
        cadastroEstado.setVisible(true);
    }


}
