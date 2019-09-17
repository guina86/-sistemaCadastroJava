/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.old;

import db.EstadoDao;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import modelo.Estado;
import utilitarios.EstadoTM;
import view.componentes.PainelGBL;

/**
 *
 * @author Leandro Guina
 */
public class CadastroEstado extends JDialog {

    private static CadastroEstado instance = null;
    private final EstadoDao estadoDao;
    private final EstadoTM modelo;
    private int current;
    private String modoEdicao;
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
    private JTextField campoId;
    private JTextField campoNome;
    private JTextField campoSigla;
    private JScrollPane scrollTabela;
    private JLabel labelId;
    private JLabel labelNome;
    private JLabel labelSigla;
    private JTable tabelaEstado;
    private PainelGBL painel;

    private CadastroEstado() {
        estadoDao = new EstadoDao();
        modelo = new EstadoTM(estadoDao.getAll());
        current = 0;
        modoEdicao = "";
        inicializa();
    }
    
    public static CadastroEstado getInstance(){
        if(instance == null){
            instance = new CadastroEstado();
        }
        return instance;
    }

    private void inicializa() {
        labelId = new JLabel("Id:", JLabel.RIGHT);
        labelNome = new JLabel("Nome:", JLabel.RIGHT);
        labelSigla = new JLabel("Sigla:", JLabel.RIGHT);
        campoId = new JTextField();
        campoNome = new JTextField();
        campoSigla = new JTextField();
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
        tabelaEstado = new JTable(modelo);
        scrollTabela = new JScrollPane();
        painel = new PainelGBL();

        campoId.setEnabled(false);

        CompoundBorder compBorder = BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8),
                BorderFactory.createTitledBorder(null, "Cadastro de estado", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.PLAIN, 18), Color.GRAY));
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
        botaoProximo.setPreferredSize(new java.awt.Dimension(60,60));
        botaoProximo.addActionListener(e -> botaoProximoActionPerformed(e));

        botaoUltimo.setToolTipText("Ultimo");
        botaoUltimo.setPreferredSize(new java.awt.Dimension(60,60));
        botaoUltimo.addActionListener(e -> botaoUltimoActionPerformed(e));

        botaoAnterior.setToolTipText("Anterior");
        botaoAnterior.setPreferredSize(new java.awt.Dimension(60,60));
        botaoAnterior.addActionListener(e -> botaoAnteriorActionPerformed(e));

        botaoCancela.setToolTipText("Voltar");
        botaoCancela.setPreferredSize(new java.awt.Dimension(60,60));
        botaoCancela.addActionListener(e -> botaoCancelaActionPerformed(e));

        tabelaEstado.getColumnModel().getColumn(0).setPreferredWidth(20);
        tabelaEstado.getColumnModel().getColumn(0).setResizable(false);
        tabelaEstado.getColumnModel().getColumn(1).setPreferredWidth(200);
        tabelaEstado.getColumnModel().getColumn(1).setResizable(false);
        tabelaEstado.getColumnModel().getColumn(2).setPreferredWidth(50);
        tabelaEstado.getColumnModel().getColumn(2).setResizable(false);
        tabelaEstado.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelaEstado.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tabelaEstado.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tabelaEstadoMouseClicked(e);
            }
        });
        
        scrollTabela.setViewportView(tabelaEstado);
        scrollTabela.setPreferredSize(new Dimension(200, 250));

        painel.adiciona(labelId, 0, 0);
        painel.adiciona(campoId, 2, 0);
        painel.adiciona(labelNome, 0, 2);
        painel.adiciona(campoNome, 2, 2, 4, 2);
        painel.adiciona(labelSigla, 6, 2);
        painel.adiciona(campoSigla, 8, 2);
        painel.adiciona(botaoNovo, 0, 4);
        painel.adiciona(botaoSalva, 2, 4);
        painel.adiciona(botaoEdita, 4, 4);
        painel.adiciona(botaoApaga, 6, 4);
        painel.adiciona(botaoCancela, 8, 4);
        painel.adiciona(botaoVolta, 10, 4);
        painel.adiciona(scrollTabela, 0, 6, 12, 12);
        painel.adiciona(botaoPrimeiro, 0, 18);
        painel.adiciona(botaoAnterior, 2, 18);
        painel.adiciona(botaoProximo, 4, 18);
        painel.adiciona(botaoUltimo, 6, 18);

        add(painel);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de estado");
        setResizable(false);
//        setPreferredSize(new Dimension(390, 530));
        setInterface(false, false, true, false, false, false, true, false, true, true);
        pack();
        setLocationRelativeTo(null);
    }

    private void setInterface(boolean nome, boolean sigla, boolean novo, boolean salva, boolean edita, boolean apaga, boolean navegacao, boolean cancela, boolean tabela, boolean limpaCampos) {
        limpaCampos(limpaCampos);
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
    }

    private void limpaCampos(boolean opcao) {
        if (opcao) {
            campoId.setText("");
            campoNome.setText("");
            campoSigla.setText("");
            tabelaEstado.clearSelection();
        }
    }

    private void preencherCampos(Estado estado) {
        campoId.setText(estado.getId().toString());
        campoNome.setText(estado.getNome());
        campoSigla.setText(estado.getSigla());
    }

    private void botaoNovoActionPerformed(java.awt.event.ActionEvent evt) {
        modoEdicao = "salvar";
        tabelaEstado.setRowSelectionAllowed(false);
        setInterface(true, true, false, true, false, false, false, true, false, true);
        campoNome.requestFocus();
    }

    private void botaoSalvaActionPerformed(java.awt.event.ActionEvent evt) {
        if (modoEdicao.equals("salvar")) {
            Estado estado = new Estado(campoNome.getText(), campoSigla.getText());
            int id = estadoDao.save(estado);
            estado.setId(id);
            modelo.add(estado);
        } else if (modoEdicao.equals("update")) {
            Estado estado = new Estado(Integer.valueOf(campoId.getText()), campoNome.getText(), campoSigla.getText());
            estadoDao.update(estado);
            modelo.setValueAt(estado, current);
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
        int option = JOptionPane.showConfirmDialog(this, "Tem certeza que quer excluir " + campoNome.getText() + " " + campoSigla.getText(), "Confirmação", JOptionPane.YES_NO_OPTION);
        if (option == 0) {
            int id = Integer.parseInt(campoId.getText());
            estadoDao.delete(id);
            modelo.remove(current);
        }
        setInterface(false, false, true, false, false, false, true, false, true, true);
    }

    private void botaoVoltaActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
    }

    private void tabelaEstadoMouseClicked(java.awt.event.MouseEvent evt) {
        current = tabelaEstado.getSelectedRow();
        Estado estado = modelo.get(current);
        preencherCampos(estado);
        setInterface(false, false, true, false, true, true, true, false, true, false);
    }

    private void botaoPrimeiroActionPerformed(java.awt.event.ActionEvent evt) {
        current = 0;
        tabelaEstado.setRowSelectionInterval(current, current);
        Estado estado = modelo.get(0);
        preencherCampos(estado);
        setInterface(false, false, true, false, true, true, true, false, true, false);
    }

    private void botaoProximoActionPerformed(java.awt.event.ActionEvent evt) {
        int size = modelo.getRowCount();
        if (campoId.getText().equals("")) {
            current = 0;
        } else if (size > 1 && current < size - 1) {
            current++;
        }
        tabelaEstado.setRowSelectionInterval(current, current);
        Estado estado = modelo.get(current);
        preencherCampos(estado);
        setInterface(false, false, true, false, true, true, true, false, true, false);
    }

    private void botaoUltimoActionPerformed(java.awt.event.ActionEvent evt) {
        current = modelo.getRowCount() - 1;
        tabelaEstado.setRowSelectionInterval(current, current);
        Estado estado = modelo.get(current);
        preencherCampos(estado);
        setInterface(false, false, true, false, true, true, true, false, true, false);
    }

    private void botaoAnteriorActionPerformed(java.awt.event.ActionEvent evt) {
        if (current > 0) {
            current--;
        }
        tabelaEstado.setRowSelectionInterval(current, current);
        Estado estado = modelo.get(current);
        preencherCampos(estado);
        setInterface(false, false, true, false, true, true, true, false, true, false);
    }

    private void botaoCancelaActionPerformed(java.awt.event.ActionEvent evt) {
        setInterface(false, false, true, false, false, false, true, false, true, true);
    }

}
