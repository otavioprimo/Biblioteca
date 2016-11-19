/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visao;

import Controle.CtrlAcervo;
import Controle.CtrlEmprestimo;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author aluno6812
 */
public class ViewEmprestimo extends javax.swing.JFrame {

    public int idCliente;
    public int idFunc;
    public int idLivro;
    public int quantidadeLivro;
    public int idAcervo;
    public String nomeFunc;
    public String tituloLivro;
    public String nomeCliente;

    private int idLivroNoAcervo;
    private String devolvido;
    private int idEmprestimo;

    /**
     * Creates new form ViewEmprestimo
     */
    public ViewEmprestimo() {
        initComponents();
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                ViewPrincipal principal = new ViewPrincipal();
                principal.setVisible(true);
            }
        });
        listarTabelaEmprestimo();
        getContentPane().setBackground(Color.black);
    }

    public static Date addDia(Date data, int qtd) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        cal.add(Calendar.DAY_OF_MONTH, qtd);
        return cal.getTime();
    }

    public static int verificaAumentoDev(Date data, Date devolucao) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(devolucao);
        cal2.add(Calendar.DATE, -cal.get(Calendar.DAY_OF_MONTH));
        return cal2.get(Calendar.DAY_OF_MONTH);
    }

    private void listarTabelaEmprestimo() {
        CtrlEmprestimo.listar(jTableEmprestimo);
    }

    private void preencherCampos() {
        txtRet.setText("" + jTableEmprestimo.getValueAt(jTableEmprestimo.getSelectedRow(), 5));
        txtDev.setText("" + jTableEmprestimo.getValueAt(jTableEmprestimo.getSelectedRow(), 6));
        devolvido = "" + jTableEmprestimo.getValueAt(jTableEmprestimo.getSelectedRow(), 7);
        idEmprestimo = Integer.parseInt("" + jTableEmprestimo.getValueAt(jTableEmprestimo.getSelectedRow(), 0));
        idLivroNoAcervo = Integer.parseInt("" + jTableEmprestimo.getValueAt(jTableEmprestimo.getSelectedRow(), 1));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtLivro = new javax.swing.JTextField();
        txtCliente = new javax.swing.JTextField();
        txtFuncionario = new javax.swing.JTextField();
        txtRet = new javax.swing.JTextField();
        txtDev = new javax.swing.JTextField();
        brnSalvar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableEmprestimo = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        btnAdicionaLivro = new javax.swing.JButton();
        btnAtualizarDevolução = new javax.swing.JButton();
        btnEfetuarDev = new javax.swing.JButton();
        txtPesquisaCliente = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Livro");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Cliente");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Funcionario");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Dt Retirada");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Dt Devolução");

        txtLivro.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtLivro.setFocusable(false);

        txtCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtCliente.setFocusable(false);

        txtFuncionario.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtFuncionario.setFocusable(false);

        txtRet.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtRet.setFocusable(false);

        txtDev.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtDev.setFocusable(false);

        brnSalvar.setBackground(new java.awt.Color(0, 0, 0));
        brnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/checked.png"))); // NOI18N
        brnSalvar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        brnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brnSalvarActionPerformed(evt);
            }
        });

        btnEditar.setBackground(new java.awt.Color(0, 0, 0));
        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/edit.png"))); // NOI18N

        btnExcluir.setBackground(new java.awt.Color(0, 0, 0));
        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/unchecked.png"))); // NOI18N

        jTableEmprestimo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTableEmprestimo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableEmprestimoMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTableEmprestimo);

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("EMPRESTIMOS");

        btnAdicionaLivro.setBackground(new java.awt.Color(0, 0, 0));
        btnAdicionaLivro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/add_1.png"))); // NOI18N
        btnAdicionaLivro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionaLivroActionPerformed(evt);
            }
        });

        btnAtualizarDevolução.setText("Atualizar Devolução");
        btnAtualizarDevolução.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtualizarDevoluçãoActionPerformed(evt);
            }
        });

        btnEfetuarDev.setText("Efetuar Devolução");
        btnEfetuarDev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEfetuarDevActionPerformed(evt);
            }
        });

        txtPesquisaCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtPesquisaCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisaClienteKeyReleased(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Pesquisar Cliente:");

        jButton1.setText("Verificar Atrasados");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jButton2.setText("Atualizar Tabela");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(28, 28, 28)
                                                .addComponent(jLabel6)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 769, Short.MAX_VALUE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                        .addGap(12, 12, 12)
                                                        .addComponent(jLabel1)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(txtLivro)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(btnAdicionaLivro))
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                                .addComponent(jLabel3)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(txtFuncionario))
                                                            .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jLabel2)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(txtCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)))
                                                        .addGap(59, 59, 59))
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                        .addComponent(jLabel9)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addComponent(brnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addComponent(txtPesquisaCliente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                            .addGap(20, 20, 20)
                                                            .addComponent(jLabel4)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(txtRet, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addComponent(jLabel5)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(txtDev, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addComponent(btnAtualizarDevolução)
                                                            .addComponent(btnEfetuarDev))))
                                                .addGap(29, 29, 29)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(398, 398, 398)
                                        .addComponent(jButton1)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addComponent(jButton2)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(txtLivro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(btnAdicionaLivro))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(23, 23, 23)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(txtRet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(txtDev, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(btnAtualizarDevolução)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(66, 66, 66)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(brnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(45, 45, 45))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEfetuarDev)
                                .addGap(18, 18, 18)))
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtPesquisaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1)
                            .addComponent(jButton2))
                        .addGap(29, 29, 29)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdicionaLivroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionaLivroActionPerformed
        ViewEmprestimoDados emprestimoCliente = new ViewEmprestimoDados();
        emprestimoCliente.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_btnAdicionaLivroActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        txtCliente.setText(nomeCliente);
        txtFuncionario.setText(nomeFunc);
        txtLivro.setText(tituloLivro);
        // System.out.println("IDCliente: " + idCliente + "\nIDFunc: " + idFunc + "\nIDLivro: " + idLivro + "\n______________");
    }//GEN-LAST:event_formWindowOpened

    private void btnAtualizarDevoluçãoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarDevoluçãoActionPerformed

        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");

        String mensagem = "Deseja Atualizar a data de entregar para mais 10 dias?";
        String titulo = "Atualizar Data Entrega";
        int resposta = JOptionPane.showConfirmDialog(null, mensagem, titulo, JOptionPane.YES_NO_OPTION);
        if (resposta == JOptionPane.YES_OPTION) {
            if (devolvido.equals("S")) {
                JOptionPane.showMessageDialog(null, "Este Livro já foi devolvido!");
            } else {
                try {
                    Date dataDev = formatador.parse(txtDev.getText());
                    Date dataRet = formatador.parse(txtRet.getText());

                    int diferenca = verificaAumentoDev(dataRet, dataDev);
                    if (diferenca < 20) {
                        CtrlEmprestimo.editar(idEmprestimo, 'N', formatador.format(addDia(dataDev, 10)));
                    } else {
                        JOptionPane.showMessageDialog(null, "Não é possível Aumentar o tempo de empréstimo! \nEste emprestimo possui "
                                + "tempo maior que 20 dias\nTempo de empréstimo: " + diferenca + " dias");
                    }

                } catch (ParseException ex) {
                    ex.printStackTrace();
                }

                listarTabelaEmprestimo();
            }
        }
    }//GEN-LAST:event_btnAtualizarDevoluçãoActionPerformed

    private void brnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brnSalvarActionPerformed
        String mensagem = "Deseja realizar o emprestimo do livro: " + tituloLivro + " para o cliente " + nomeCliente + "?";
        String titulo = "Atualizar Data Entrega";
        int resposta = JOptionPane.showConfirmDialog(null, mensagem, titulo, JOptionPane.YES_NO_OPTION);
        if (resposta == JOptionPane.YES_OPTION) {
            int novaQtd;
            novaQtd = quantidadeLivro - 1;
            try {
                Date hoje = new Date();
                SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
                String dataAtual = formatador.format(hoje);
                String dataDev = formatador.format(addDia(hoje, 10));

                //System.out.println("Quantidade: " + novaQtd);
                //System.out.println("IdLivro: " + idLivro + "\nIdCliente: " + idCliente + "\nIdFunc: " + idFunc + "\nData Retirada: " + dataAtual + "\nData Entrega: " + dataDev);
                CtrlAcervo.editar(idLivro, novaQtd, idAcervo, tituloLivro);
                CtrlEmprestimo.salvar(idLivro, idCliente, idFunc, dataAtual, dataDev, 'N');
                listarTabelaEmprestimo();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_brnSalvarActionPerformed

    private void btnEfetuarDevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEfetuarDevActionPerformed
        if ("S".equals(devolvido)) {
            JOptionPane.showMessageDialog(null, "Este livro ja foi marcado como devolvido");
        } else {
            String mensagem = "Deseja Efetuar a entrega deste livro?";
            String titulo = "Entregar Livro";
            int resposta = JOptionPane.showConfirmDialog(null, mensagem, titulo, JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                try {
                    CtrlAcervo.devolucaoLivro(idLivroNoAcervo);
                    CtrlEmprestimo.editar(idEmprestimo, 'S', txtDev.getText());
                    listarTabelaEmprestimo();
                } catch (Exception e) {                    
                }
            }
        }
    }//GEN-LAST:event_btnEfetuarDevActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        CtrlEmprestimo.listarAtrasados(jTableEmprestimo);// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTableEmprestimoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableEmprestimoMouseClicked
        preencherCampos();
    }//GEN-LAST:event_jTableEmprestimoMouseClicked

    private void txtPesquisaClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisaClienteKeyReleased
        CtrlEmprestimo.listarClienteNome(jTableEmprestimo, txtPesquisaCliente.getText().trim());
    }//GEN-LAST:event_txtPesquisaClienteKeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        listarTabelaEmprestimo();
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(ViewEmprestimo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewEmprestimo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewEmprestimo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewEmprestimo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewEmprestimo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton brnSalvar;
    private javax.swing.JButton btnAdicionaLivro;
    private javax.swing.JButton btnAtualizarDevolução;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEfetuarDev;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable jTableEmprestimo;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtDev;
    private javax.swing.JTextField txtFuncionario;
    private javax.swing.JTextField txtLivro;
    private javax.swing.JTextField txtPesquisaCliente;
    private javax.swing.JTextField txtRet;
    // End of variables declaration//GEN-END:variables
}
