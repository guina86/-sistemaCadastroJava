/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.componentes;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JPanel;

/**
 *
 * @author Leandro Guina
 */
public class PainelGBL extends JPanel{
    
    private GridBagConstraints gbc;

    public PainelGBL() {
        super(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(4, 3, 4, 3);
    }
    
    public void adiciona(Component componente, int gridx, int gridy, int largura, int altura) {
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.gridwidth = largura;
        gbc.gridheight = altura;
        add(componente, gbc);
    }

    public void adiciona(Component componente, int gridx, int gridy) {
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        add(componente, gbc);
    }

    public void setPeso(double peso) {
        gbc.weightx = peso;
        gbc.weighty = peso;
    }

    public void setPeso(double pesoX, double pesoY) {
        gbc.weightx = pesoX;
        gbc.weighty = pesoY;
    }
    
    
}
