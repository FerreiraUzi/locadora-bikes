import dao.BicicletaDAO;
import dao.ClienteDAO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import model.Bicicleta;
import model.Cliente;
import model.Locacao;
import dao.LocacaoDAO;
import model.Manutencao;
import dao.ManutencaoDAO;
import java.text.SimpleDateFormat;


public class TelaRelatorios extends javax.swing.JFrame {

    private static final Logger logger = Logger.getLogger(TelaRelatorios.class.getName());

    public TelaRelatorios() {
        initComponents();

        // ActionListener para o botão "Gerar"
        btnGerar.addActionListener(e -> {
            String tipo = (String) cbTipoRelatorio.getSelectedItem();
            gerarRelatorio(tipo);
        });
    }

    private void listarClientes() {
        List<Cliente> lista = new ClienteDAO().read();
        DefaultTableModel model = new DefaultTableModel(
                new Object[]{"ID", "Nome", "CPF", "Telefone", "Email"}, 0);
        for (Cliente c : lista) {
            model.addRow(new Object[]{c.getId(), c.getNome(), c.getCpf(), c.getTelefone(), c.getEmail()});
        }
        tabelaRelatorios.setModel(model);
    }

    private void listarBicicletas() {
        List<Bicicleta> lista = new BicicletaDAO().read();
        DefaultTableModel model = new DefaultTableModel(
                new Object[]{"ID", "Código", "Status"}, 0);
        for (Bicicleta b : lista) {
            model.addRow(new Object[]{b.getId(), b.getCodigo(), b.getStatus()});
        }
        tabelaRelatorios.setModel(model);
    }

    

    private void listarLocacoes() {
    List<Locacao> lista = new LocacaoDAO().read();
    DefaultTableModel model = new DefaultTableModel(
            new Object[]{"Cliente ID", "Bicicleta ID", "Status"}, 0);

    for (Locacao l : lista) {
        model.addRow(new Object[]{
            l.getClienteId(),
            l.getBicicletaId(),
            l.getStatus()  // Aqui você já tem "Ativa" ou "Devolvida" se preencher no DAO
        });
    }

    tabelaRelatorios.setModel(model);
}


    private void listarManutencoes() {
        List<Manutencao> lista = new ManutencaoDAO().read(); // pega do banco
        DefaultTableModel model = new DefaultTableModel(
                new Object[]{"Bicicleta", "Descrição", "Data"}, 0);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // formata a data

        for (Manutencao m : lista) {
            model.addRow(new Object[]{
                m.getBicicletaId(),
                m.getDescricao(),
                sdf.format(m.getData())
            });
        }
        tabelaRelatorios.setModel(model);
    }

    public void gerarRelatorio(String tipo) {
        switch (tipo) {
            case "Clientes" -> listarClientes();
            case "Bicicletas" -> listarBicicletas();
            case "Locações" -> listarLocacoes();
            case "Manutenções" -> listarManutencoes();
            default -> logger.warning(() -> "Tipo de relatório desconhecido: " + tipo);
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        cbTipoRelatorio = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        btnGerar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaRelatorios = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel1.setText("Tela de Relatórios:");

        cbTipoRelatorio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Clientes", "Bicicletas", "Locações", "Manutenções" }));
        cbTipoRelatorio.setName("cbTipoRelatorio"); // NOI18N

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("Selecione o tipo de relatório desejado:");

        btnGerar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnGerar.setText("Gerar");
        btnGerar.setName("btnGerar"); // NOI18N

        tabelaRelatorios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabelaRelatorios.setName("tabelaRelatorios"); // NOI18N
        jScrollPane1.setViewportView(tabelaRelatorios);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(cbTipoRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGerar)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jLabel1)))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbTipoRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnGerar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new TelaRelatorios().setVisible(true));
    }
    

    
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGerar;
    private javax.swing.JComboBox<String> cbTipoRelatorio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tabelaRelatorios;
    // End of variables declaration//GEN-END:variables
}
