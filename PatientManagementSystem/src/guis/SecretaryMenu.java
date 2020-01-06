/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guis;
import javax.swing.table.DefaultTableModel;
import system.*;
import users.*;
import java.awt.Dialog;
import javax.swing.JOptionPane;

/**
 *
 * @author LoL-1
 */
public class SecretaryMenu extends javax.swing.JFrame {

    /**
     * Creates new form SecretaryMenu
     */
    public SecretaryMenu() {
        initComponents();
        getUserInfo();
        setAccountRequests();
        setAppointmentRequests();
        setPrescriptions();
        setMedicine();
        setMedicineRequests();
        setPatients();
        setTerminations();
    }
    
    private void getUserInfo()
    {
        this.txtUserAccountType.setText("Secretary");
        this.txtUserID.setText(User.loggedUser.getID());
        this.txtUserName.setText(User.loggedUser.getForename() + " " + User.loggedUser.getSurname());
        this.txtUserAddress.setText(User.loggedUser.getAddress());
    }
    
    
    private void setAccountRequests()
    {
        String[][] patientRequests = new String[AccountRequest.accountRequests.length][5];
        
        DefaultTableModel model = (DefaultTableModel) this.tblPatientRequests.getModel();
        
        int rowCount = model.getRowCount();
        if(rowCount > 0)
        {
            for (int i = rowCount - 1; i >= 0; i--) 
            {
                model.removeRow(i);
            }
        }

        int i = 0;
        for(AccountRequest accountRequest : AccountRequest.accountRequests)
        {
            patientRequests[i][0] = accountRequest.getForename();
            patientRequests[i][1] = accountRequest.getSurname();
            patientRequests[i][2] = accountRequest.getAddress();
            patientRequests[i][3] = accountRequest.getGender();
            patientRequests[i][4] = accountRequest.getDOB();
            
            i++;
        }
        
        for(String[] data : patientRequests)
        {
            model.addRow(data);
        }
    }
    
    private void setAppointmentRequests()
    {
        
        DefaultTableModel model = (DefaultTableModel) this.tblAppointmentRequests.getModel();
        
        String[][] appointmentRequests = new String[AppointmentRequest.appointmentRequests.length][5];
        
        int i = 0;
        for(AppointmentRequest appointmentRequest : AppointmentRequest.appointmentRequests)
        {
            Patient patient = appointmentRequest.getPatient();
            
            String forename = patient.getForename();
            String surname = patient.getSurname();

            appointmentRequests[i][0] = patient.getID();
            appointmentRequests[i][1] = forename + " " + surname;
                
            Doctor doctor = appointmentRequest.getDoctor();
            appointmentRequests[i][2] = doctor.getID();
            appointmentRequests[i][3] = "Dr. " + doctor.getSurname();
            appointmentRequests[i][4] = appointmentRequest.getDate();

            i++;
        }
        
        for(String[] data : appointmentRequests)
        {
            model.addRow(data);
        }
    }
    
    private void setPrescriptions()
    {   
        DefaultTableModel model = (DefaultTableModel) this.tblPrescriptions.getModel();

        int rowCount = model.getRowCount();
        if(rowCount > 0)
        {
            for (int i = rowCount - 1; i >= 0; i--) {
                model.removeRow(i);
            }
        }
        
        for(PrescriptionRequest prescriptionRequest : PrescriptionRequest.prescriptionRequests)
        {
            String name = "";
            for(Patient patient : Patient.patients)
            {
                if(prescriptionRequest.getPatient().getID().equals(patient.getID())){
                    name = patient.getForename() + " " + patient.getSurname();
                    break;
                }
            }
            
            String[] data = {
                prescriptionRequest.getDoctor().getID(),
                prescriptionRequest.getPatient().getID(),
                name,
                prescriptionRequest.getMedicine().getName(),
                Integer.toString(prescriptionRequest.getQuantity())
            };

            model.addRow(data);  
        }
    }
    
    private void setMedicine()
    {
        DefaultTableModel model = (DefaultTableModel) this.tblStock.getModel();
        int rowCount = model.getRowCount();
        if(rowCount > 0)
        {
            for (int i = rowCount - 1; i >= 0; i--) {
                model.removeRow(i);
            }
        }
        
        for(Medicine medicine : Medicine.medicines)
        {        
            String[] data = {
                medicine.getName(),
                Integer.toString(medicine.getStock())
            };

            model.addRow(data); 
            
        }
    }
    
    private void orderStock(String name, int quantity)
    {
        boolean exist = false;
        for(Medicine medicine : Medicine.medicines)
        {
            if(medicine.getName().equals(name))
            {
                int stock = medicine.getStock();
                stock += quantity;
                medicine.setStock(stock);
                
                DefaultTableModel model = (DefaultTableModel) this.tblStock.getModel();
                int rowCount = model.getRowCount();
                
                for (int i = rowCount - 1; i >= 0; i--) {
                    model.removeRow(i);
                }
                
                setMedicine();
                exist = true;
                break;
            }
        }
        
        if(exist == false)
        {
            orderNewMedicine(name, quantity);
        }
    }
    
    private void setMedicineRequests()
    {
        DefaultTableModel model = (DefaultTableModel) this.tblMedicineRequest.getModel();
        int rowCount = model.getRowCount();
        if(rowCount > 0)
        {
            for (int i = rowCount - 1; i >= 0; i--) {
                model.removeRow(i);
            }
        }
        
        for(MedicineRequest medicineRequest : MedicineRequest.medicineRequests)
        {        
            String[] data = {
                medicineRequest.getName(),
                Integer.toString(medicineRequest.getStock())
            };

            model.addRow(data); 
            
        }
    }
    
    private void orderNewMedicine(String name, int quantity)
    {
        for(MedicineRequest medicineRequest : MedicineRequest.medicineRequests)
        {
            if(medicineRequest.getName().equals(name))
            {
                Medicine newMedicine = new Medicine(name, quantity);
                newMedicine.addMedicine(newMedicine);

                medicineRequest.removeMedicineRequest(medicineRequest);
                
                setMedicineRequests();
                break; 
            }
        }
    }
    
    private void setPatients()
    {   
        DefaultTableModel model = (DefaultTableModel) this.tblPatients.getModel();
        int rowCount = model.getRowCount();
        
        if(rowCount > 0)
        {
            for (int i = rowCount - 1; i >= 0; i--) {
                model.removeRow(i);
            }
        }
        
        for(Patient patient : Patient.patients)
        {        
            String[] data = {
                patient.getID(),
                patient.getForename(),
                patient.getSurname(),
                patient.getAddress(),
                patient.getGender(),
                patient.getDOB()
            };

            model.addRow(data); 
        }
    }
    
    private void removePatient(String id)
    {
        Patient[] temp = new Patient[Patient.patients.length - 1];
        int i = 0;
        for(Patient patient : Patient.patients)
        {
            if(!(patient.getID().equals(id)))
            {
               temp[i] = patient;
               i++;
            }
        }
        Patient.patients = temp;
    }
    
    private void setTerminations()
    {
        DefaultTableModel model = (DefaultTableModel) this.tblTermination.getModel();
        int rowCount = model.getRowCount();
        
        if(rowCount > 0)
        {
            for (int i = rowCount - 1; i >= 0; i--) {
                model.removeRow(i);
            }
        }
        
        for(TerminationRequest terminationRequest : TerminationRequest.terminationRequests)
        {
            Patient patient = terminationRequest.getPatient();
            String[] data = {
                patient.getID(),
                patient.getForename(),
                patient.getSurname(),
                patient.getAddress(),
                patient.getGender(),
                patient.getDOB()
            };
            
            model.addRow(data);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblMain = new java.awt.Label();
        menuSecretary = new javax.swing.JTabbedPane();
        tabUserInfo = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        lblAccountType1 = new javax.swing.JLabel();
        txtUserAccountType = new javax.swing.JTextField();
        lblUserInfo1 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        lblUserID1 = new javax.swing.JLabel();
        txtUserID = new javax.swing.JTextField();
        jPanel18 = new javax.swing.JPanel();
        lblUserName2 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        txtUserAddress = new javax.swing.JTextArea();
        jPanel16 = new javax.swing.JPanel();
        lblUserName1 = new javax.swing.JLabel();
        txtUserName = new javax.swing.JTextField();
        tabApprovePatient = new javax.swing.JPanel();
        lblApproveAccounts = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPatientRequests = new javax.swing.JTable();
        lblNewRequests = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblForename = new javax.swing.JLabel();
        lblAddress = new javax.swing.JLabel();
        txtPatientForename = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtPatientAddress = new javax.swing.JTextArea();
        btnApprovePatient = new javax.swing.JButton();
        lblDOB = new javax.swing.JLabel();
        txtPatientGender = new javax.swing.JTextField();
        lblGender = new javax.swing.JLabel();
        txtPatientDOB = new javax.swing.JTextField();
        lblSurname = new javax.swing.JLabel();
        txtPatientSurname = new javax.swing.JTextField();
        tabSetAppointment = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        tabApprovePatient1 = new javax.swing.JPanel();
        lblApproveAccounts1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblAppointmentRequests = new javax.swing.JTable();
        lblNewRequests1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblForename1 = new javax.swing.JLabel();
        lblAddress1 = new javax.swing.JLabel();
        txtAppointmentPatientID = new javax.swing.JTextField();
        btnApproveAppointment = new javax.swing.JButton();
        txtAppointmentDate = new javax.swing.JTextField();
        lblGender1 = new javax.swing.JLabel();
        lblSurname1 = new javax.swing.JLabel();
        txtAppointmentPatient = new javax.swing.JTextField();
        txtAppointmentDoctorID = new javax.swing.JTextField();
        lblAddress2 = new javax.swing.JLabel();
        txtAppointmentDoctor = new javax.swing.JTextField();
        btnDeclineAppointment = new javax.swing.JButton();
        tabGiveMedicine = new javax.swing.JPanel();
        tabApprovePatient2 = new javax.swing.JPanel();
        lblApproveAccounts2 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblPrescriptions = new javax.swing.JTable();
        lblNewRequests2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lblPatientID = new javax.swing.JLabel();
        lblDoctorID = new javax.swing.JLabel();
        txtPrescriptionPatientID = new javax.swing.JTextField();
        btnApprovePrescription = new javax.swing.JButton();
        lblSurname2 = new javax.swing.JLabel();
        txtPrescriptionPatient = new javax.swing.JTextField();
        txtPrescriptionDoctorID = new javax.swing.JTextField();
        lblMedicine = new javax.swing.JLabel();
        txtPrescriptionMedicine = new javax.swing.JTextField();
        lblQuantity = new javax.swing.JLabel();
        txtPrescriptionQuantity = new javax.swing.JTextField();
        tabOrderStock = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        lblStock1 = new javax.swing.JLabel();
        lblMedicineName = new javax.swing.JLabel();
        lblMedicineQuantity = new javax.swing.JLabel();
        txtMedicineQuantity = new javax.swing.JTextField();
        txtMedicineName = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        lblNumItems = new javax.swing.JLabel();
        txtOrderItems = new javax.swing.JTextField();
        btnOrder = new javax.swing.JButton();
        lblMedicineMain = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        lblStock = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblStock = new javax.swing.JTable();
        jPanel14 = new javax.swing.JPanel();
        lblMedicineRequests = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        tblMedicineRequest = new javax.swing.JTable();
        tabRemovePatient = new javax.swing.JPanel();
        tabApprovePatient3 = new javax.swing.JPanel();
        lblApproveAccounts3 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        lblPatientID1 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        btnRemove = new javax.swing.JButton();
        lblPatientName1 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        lblPatientAddress = new javax.swing.JLabel();
        lblPatientGender = new javax.swing.JLabel();
        txtGender = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtAddress = new javax.swing.JTextArea();
        lblPatientDOB = new javax.swing.JLabel();
        txtDOB = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblPatients = new javax.swing.JTable();
        tabApproveTermination = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        lblApproveAccounts4 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblTermination = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        lblPatientID2 = new javax.swing.JLabel();
        txtTerminatePatientID = new javax.swing.JTextField();
        btnTerminate = new javax.swing.JButton();
        lblPatientName2 = new javax.swing.JLabel();
        txtTerminateName = new javax.swing.JTextField();
        lblPatientAddress1 = new javax.swing.JLabel();
        lblPatientGender1 = new javax.swing.JLabel();
        txtTerminateGender = new javax.swing.JTextField();
        jScrollPane9 = new javax.swing.JScrollPane();
        txtTerminateAddress = new javax.swing.JTextArea();
        lblPatientDOB1 = new javax.swing.JLabel();
        txtTerminateDOB = new javax.swing.JTextField();
        btnLogout = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblMain.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblMain.setName(""); // NOI18N
        lblMain.setText("Patient Management System");

        menuSecretary.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jPanel12.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblAccountType1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblAccountType1.setText("Account Type:");

        txtUserAccountType.setEditable(false);
        txtUserAccountType.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAccountType1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUserAccountType)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblAccountType1)
                    .addComponent(txtUserAccountType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblUserInfo1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblUserInfo1.setText("User Information");

        jPanel13.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblUserID1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblUserID1.setText("User ID:");

        txtUserID.setEditable(false);
        txtUserID.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(lblUserID1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtUserID, javax.swing.GroupLayout.PREFERRED_SIZE, 934, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblUserID1)
                    .addComponent(txtUserID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel18.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblUserName2.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblUserName2.setText("Address:");

        txtUserAddress.setEditable(false);
        txtUserAddress.setColumns(20);
        txtUserAddress.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txtUserAddress.setRows(5);
        jScrollPane10.setViewportView(txtUserAddress);

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lblUserName2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane10)
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUserName2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel16.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblUserName1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblUserName1.setText("User Name:");

        txtUserName.setEditable(false);
        txtUserName.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblUserName1)
                .addGap(18, 18, 18)
                .addComponent(txtUserName)
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblUserName1)
                    .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout tabUserInfoLayout = new javax.swing.GroupLayout(tabUserInfo);
        tabUserInfo.setLayout(tabUserInfoLayout);
        tabUserInfoLayout.setHorizontalGroup(
            tabUserInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabUserInfoLayout.createSequentialGroup()
                .addGroup(tabUserInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabUserInfoLayout.createSequentialGroup()
                        .addGap(391, 391, 391)
                        .addComponent(lblUserInfo1)
                        .addGap(0, 535, Short.MAX_VALUE))
                    .addGroup(tabUserInfoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(tabUserInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(tabUserInfoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(tabUserInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        tabUserInfoLayout.setVerticalGroup(
            tabUserInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabUserInfoLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lblUserInfo1)
                .addGap(18, 18, 18)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(311, Short.MAX_VALUE))
        );

        menuSecretary.addTab("User Information", tabUserInfo);

        lblApproveAccounts.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblApproveAccounts.setText("Approve Patient Accounts");

        tblPatientRequests.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        tblPatientRequests.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Forename", "Surname", "Address", "Gender", "DOB"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPatientRequests.setRowHeight(25);
        tblPatientRequests.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPatientRequestsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPatientRequests);
        if (tblPatientRequests.getColumnModel().getColumnCount() > 0) {
            tblPatientRequests.getColumnModel().getColumn(4).setHeaderValue("DOB");
        }

        lblNewRequests.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lblNewRequests.setText("New Requests:");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblForename.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblForename.setText("Patient Forename:");

        lblAddress.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblAddress.setText("Patient Address:");

        txtPatientForename.setEditable(false);
        txtPatientForename.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        txtPatientAddress.setEditable(false);
        txtPatientAddress.setColumns(20);
        txtPatientAddress.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txtPatientAddress.setRows(5);
        jScrollPane2.setViewportView(txtPatientAddress);

        btnApprovePatient.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        btnApprovePatient.setText("Approve");
        btnApprovePatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApprovePatientActionPerformed(evt);
            }
        });

        lblDOB.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblDOB.setText("Patient DOB:");

        txtPatientGender.setEditable(false);
        txtPatientGender.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        lblGender.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblGender.setText("Patient Gender:");

        txtPatientDOB.setEditable(false);
        txtPatientDOB.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        lblSurname.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblSurname.setText("Patient Surname:");

        txtPatientSurname.setEditable(false);
        txtPatientSurname.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblAddress)
                    .addComponent(lblForename))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
                    .addComponent(txtPatientForename))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(lblSurname)
                            .addComponent(lblGender)
                            .addComponent(lblDOB))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(txtPatientSurname, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPatientDOB, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtPatientGender))))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnApprovePatient)
                        .addGap(209, 209, 209))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblForename)
                    .addComponent(txtPatientForename, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSurname)
                    .addComponent(txtPatientSurname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblAddress)
                    .addComponent(lblGender)
                    .addComponent(txtPatientGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDOB)
                    .addComponent(txtPatientDOB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55)
                .addComponent(btnApprovePatient)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                .addGap(11, 11, 11))
        );

        javax.swing.GroupLayout tabApprovePatientLayout = new javax.swing.GroupLayout(tabApprovePatient);
        tabApprovePatient.setLayout(tabApprovePatientLayout);
        tabApprovePatientLayout.setHorizontalGroup(
            tabApprovePatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabApprovePatientLayout.createSequentialGroup()
                .addGroup(tabApprovePatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabApprovePatientLayout.createSequentialGroup()
                        .addGroup(tabApprovePatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tabApprovePatientLayout.createSequentialGroup()
                                .addGap(452, 452, 452)
                                .addComponent(lblApproveAccounts))
                            .addGroup(tabApprovePatientLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblNewRequests)))
                        .addGap(0, 392, Short.MAX_VALUE))
                    .addGroup(tabApprovePatientLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(tabApprovePatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        tabApprovePatientLayout.setVerticalGroup(
            tabApprovePatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabApprovePatientLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblApproveAccounts)
                .addGap(9, 9, 9)
                .addComponent(lblNewRequests)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        menuSecretary.addTab("Approve Patient Accounts", tabApprovePatient);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblApproveAccounts1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblApproveAccounts1.setText("Approve Appointments");

        tblAppointmentRequests.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        tblAppointmentRequests.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Patient ID", "Patient Name", "Doctor ID", "Doctor Name", "Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblAppointmentRequests.setRowHeight(25);
        tblAppointmentRequests.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAppointmentRequestsMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblAppointmentRequests);

        lblNewRequests1.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lblNewRequests1.setText("New Appointments:");

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblForename1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblForename1.setText("Patient ID:");

        lblAddress1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblAddress1.setText("Doctor ID:");

        txtAppointmentPatientID.setEditable(false);
        txtAppointmentPatientID.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        btnApproveAppointment.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        btnApproveAppointment.setText("Approve");
        btnApproveAppointment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApproveAppointmentActionPerformed(evt);
            }
        });

        txtAppointmentDate.setEditable(false);
        txtAppointmentDate.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        lblGender1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblGender1.setText("Date:");

        lblSurname1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblSurname1.setText("Patient Name:");

        txtAppointmentPatient.setEditable(false);
        txtAppointmentPatient.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        txtAppointmentDoctorID.setEditable(false);
        txtAppointmentDoctorID.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        lblAddress2.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblAddress2.setText("Doctor Name:");

        txtAppointmentDoctor.setEditable(false);
        txtAppointmentDoctor.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        btnDeclineAppointment.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        btnDeclineAppointment.setText("Decline");
        btnDeclineAppointment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeclineAppointmentActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblAddress1)
                    .addComponent(lblForename1)
                    .addComponent(lblGender1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtAppointmentDate)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtAppointmentPatientID, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                            .addComponent(txtAppointmentDoctorID))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(lblSurname1)
                            .addComponent(lblAddress2))
                        .addGap(11, 11, 11)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtAppointmentPatient, javax.swing.GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE)
                            .addComponent(txtAppointmentDoctor, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(166, 166, 166)
                        .addComponent(btnApproveAppointment)
                        .addGap(18, 18, 18)
                        .addComponent(btnDeclineAppointment)
                        .addContainerGap(244, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblForename1)
                    .addComponent(txtAppointmentPatientID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSurname1)
                    .addComponent(txtAppointmentPatient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblAddress1)
                    .addComponent(txtAppointmentDoctorID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAddress2)
                    .addComponent(txtAppointmentDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnApproveAppointment)
                    .addComponent(lblGender1)
                    .addComponent(txtAppointmentDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDeclineAppointment))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout tabApprovePatient1Layout = new javax.swing.GroupLayout(tabApprovePatient1);
        tabApprovePatient1.setLayout(tabApprovePatient1Layout);
        tabApprovePatient1Layout.setHorizontalGroup(
            tabApprovePatient1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabApprovePatient1Layout.createSequentialGroup()
                .addGroup(tabApprovePatient1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabApprovePatient1Layout.createSequentialGroup()
                        .addGroup(tabApprovePatient1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tabApprovePatient1Layout.createSequentialGroup()
                                .addGap(452, 452, 452)
                                .addComponent(lblApproveAccounts1))
                            .addGroup(tabApprovePatient1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblNewRequests1)))
                        .addGap(0, 377, Short.MAX_VALUE))
                    .addGroup(tabApprovePatient1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(tabApprovePatient1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        tabApprovePatient1Layout.setVerticalGroup(
            tabApprovePatient1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabApprovePatient1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblApproveAccounts1)
                .addGap(9, 9, 9)
                .addComponent(lblNewRequests1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1058, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(tabApprovePatient1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 681, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(tabApprovePatient1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout tabSetAppointmentLayout = new javax.swing.GroupLayout(tabSetAppointment);
        tabSetAppointment.setLayout(tabSetAppointmentLayout);
        tabSetAppointmentLayout.setHorizontalGroup(
            tabSetAppointmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabSetAppointmentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        tabSetAppointmentLayout.setVerticalGroup(
            tabSetAppointmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabSetAppointmentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        menuSecretary.addTab("Approve Appointment", tabSetAppointment);

        tabApprovePatient2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblApproveAccounts2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblApproveAccounts2.setText("Prescription Approval");

        tblPrescriptions.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        tblPrescriptions.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Doctor ID", "Patient ID", "Patient Name", "Medicine", "Quantity"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPrescriptions.setRowHeight(25);
        tblPrescriptions.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPrescriptionsMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblPrescriptions);

        lblNewRequests2.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lblNewRequests2.setText("New Prescriptions:");

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblPatientID.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblPatientID.setText("Patient ID:");

        lblDoctorID.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblDoctorID.setText("Doctor ID:");

        txtPrescriptionPatientID.setEditable(false);
        txtPrescriptionPatientID.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        btnApprovePrescription.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        btnApprovePrescription.setText("Approve");
        btnApprovePrescription.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApprovePrescriptionActionPerformed(evt);
            }
        });

        lblSurname2.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblSurname2.setText("Patient Name:");

        txtPrescriptionPatient.setEditable(false);
        txtPrescriptionPatient.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        txtPrescriptionDoctorID.setEditable(false);
        txtPrescriptionDoctorID.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        lblMedicine.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblMedicine.setText("Medicine:");

        txtPrescriptionMedicine.setEditable(false);
        txtPrescriptionMedicine.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        lblQuantity.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblQuantity.setText("Quantity");

        txtPrescriptionQuantity.setEditable(false);
        txtPrescriptionQuantity.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblDoctorID)
                    .addComponent(lblPatientID))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtPrescriptionPatientID, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrescriptionDoctorID, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblSurname2)
                    .addComponent(lblMedicine))
                .addGap(11, 11, 11)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPrescriptionPatient)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txtPrescriptionMedicine, javax.swing.GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(lblQuantity)
                        .addGap(18, 18, 18)
                        .addComponent(txtPrescriptionQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(btnApprovePrescription)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblPatientID)
                    .addComponent(txtPrescriptionPatientID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSurname2)
                    .addComponent(txtPrescriptionPatient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblDoctorID)
                    .addComponent(txtPrescriptionDoctorID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMedicine)
                    .addComponent(txtPrescriptionMedicine, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblQuantity)
                    .addComponent(txtPrescriptionQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(btnApprovePrescription)
                .addContainerGap())
        );

        javax.swing.GroupLayout tabApprovePatient2Layout = new javax.swing.GroupLayout(tabApprovePatient2);
        tabApprovePatient2.setLayout(tabApprovePatient2Layout);
        tabApprovePatient2Layout.setHorizontalGroup(
            tabApprovePatient2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabApprovePatient2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabApprovePatient2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabApprovePatient2Layout.createSequentialGroup()
                        .addComponent(lblNewRequests2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane4)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabApprovePatient2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblApproveAccounts2)
                .addGap(417, 417, 417))
        );
        tabApprovePatient2Layout.setVerticalGroup(
            tabApprovePatient2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabApprovePatient2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lblApproveAccounts2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNewRequests2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout tabGiveMedicineLayout = new javax.swing.GroupLayout(tabGiveMedicine);
        tabGiveMedicine.setLayout(tabGiveMedicineLayout);
        tabGiveMedicineLayout.setHorizontalGroup(
            tabGiveMedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1094, Short.MAX_VALUE)
            .addGroup(tabGiveMedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabGiveMedicineLayout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tabApprovePatient2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
        tabGiveMedicineLayout.setVerticalGroup(
            tabGiveMedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 707, Short.MAX_VALUE)
            .addGroup(tabGiveMedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabGiveMedicineLayout.createSequentialGroup()
                    .addContainerGap(35, Short.MAX_VALUE)
                    .addComponent(tabApprovePatient2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(36, Short.MAX_VALUE)))
        );

        menuSecretary.addTab("Give Medicine", tabGiveMedicine);

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblStock1.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lblStock1.setText("Order Stock");

        lblMedicineName.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblMedicineName.setText("Medicine:");

        lblMedicineQuantity.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblMedicineQuantity.setText("Quantity:");

        txtMedicineQuantity.setEditable(false);
        txtMedicineQuantity.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        txtMedicineName.setEditable(false);
        txtMedicineName.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        jPanel8.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblNumItems.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblNumItems.setText("Enter Number of Items to Order:");

        txtOrderItems.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNumItems)
                .addGap(18, 18, 18)
                .addComponent(txtOrderItems)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNumItems)
                    .addComponent(txtOrderItems, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnOrder.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        btnOrder.setText("Order");
        btnOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrderActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addComponent(lblMedicineName)
                        .addGap(18, 18, 18)
                        .addComponent(txtMedicineName, javax.swing.GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(lblMedicineQuantity)
                        .addGap(18, 18, 18)
                        .addComponent(txtMedicineQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addComponent(lblStock1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(468, 468, 468)
                .addComponent(btnOrder)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblStock1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMedicineName)
                    .addComponent(lblMedicineQuantity)
                    .addComponent(txtMedicineQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMedicineName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnOrder)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblMedicineMain.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblMedicineMain.setText("Medicine");

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblStock.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lblStock.setText("Stock");

        tblStock.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        tblStock.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Medicine", "Quantity"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblStock.setRowHeight(25);
        tblStock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblStockMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblStock);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(lblStock)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane5))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblStock)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel14.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblMedicineRequests.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lblMedicineRequests.setText("Medicine Requests");

        tblMedicineRequest.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        tblMedicineRequest.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Medicine", "Quantity"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblMedicineRequest.setRowHeight(25);
        tblMedicineRequest.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMedicineRequestMouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(tblMedicineRequest);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMedicineRequests)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel14Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane11)
                    .addContainerGap()))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMedicineRequests)
                .addContainerGap(128, Short.MAX_VALUE))
            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel14Layout.createSequentialGroup()
                    .addGap(39, 39, 39)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblMedicineMain)
                .addGap(498, 498, 498))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMedicineMain)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout tabOrderStockLayout = new javax.swing.GroupLayout(tabOrderStock);
        tabOrderStock.setLayout(tabOrderStockLayout);
        tabOrderStockLayout.setHorizontalGroup(
            tabOrderStockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabOrderStockLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        tabOrderStockLayout.setVerticalGroup(
            tabOrderStockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabOrderStockLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        menuSecretary.addTab("Order Medicine", tabOrderStock);

        tabApprovePatient3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblApproveAccounts3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblApproveAccounts3.setText("Remove Patient Account");

        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblPatientID1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblPatientID1.setText("Patient ID:");

        txtID.setEditable(false);
        txtID.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        btnRemove.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        btnRemove.setText("DELETE");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        lblPatientName1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblPatientName1.setText("Patient Name:");

        txtName.setEditable(false);
        txtName.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        lblPatientAddress.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblPatientAddress.setText("Address:");

        lblPatientGender.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblPatientGender.setText("Gender");

        txtGender.setEditable(false);
        txtGender.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        txtAddress.setEditable(false);
        txtAddress.setColumns(20);
        txtAddress.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtAddress.setRows(5);
        jScrollPane7.setViewportView(txtAddress);

        lblPatientDOB.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblPatientDOB.setText("DOB:");

        txtDOB.setEditable(false);
        txtDOB.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblPatientID1)
                    .addComponent(lblPatientAddress))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(lblPatientName1))
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(txtName)
                        .addContainerGap())
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnRemove)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(lblPatientGender)
                                .addGap(18, 18, 18)
                                .addComponent(txtGender, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblPatientDOB)))
                        .addGap(18, 18, 18)
                        .addComponent(txtDOB, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(44, Short.MAX_VALUE))))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblPatientID1)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPatientName1)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(lblPatientAddress))
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblPatientGender)
                                .addComponent(txtGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblPatientDOB)
                                .addComponent(txtDOB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRemove)
                        .addGap(24, 24, 24))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        tblPatients.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        tblPatients.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Patient ID", " Forename", "Surname", "Address", "Gender", "DOB"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPatients.setRowHeight(25);
        tblPatients.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPatientsMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblPatients);

        javax.swing.GroupLayout tabApprovePatient3Layout = new javax.swing.GroupLayout(tabApprovePatient3);
        tabApprovePatient3.setLayout(tabApprovePatient3Layout);
        tabApprovePatient3Layout.setHorizontalGroup(
            tabApprovePatient3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabApprovePatient3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabApprovePatient3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane6))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabApprovePatient3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblApproveAccounts3)
                .addGap(421, 421, 421))
        );
        tabApprovePatient3Layout.setVerticalGroup(
            tabApprovePatient3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabApprovePatient3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblApproveAccounts3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout tabRemovePatientLayout = new javax.swing.GroupLayout(tabRemovePatient);
        tabRemovePatient.setLayout(tabRemovePatientLayout);
        tabRemovePatientLayout.setHorizontalGroup(
            tabRemovePatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1088, Short.MAX_VALUE)
            .addGroup(tabRemovePatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabRemovePatientLayout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tabApprovePatient3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
        tabRemovePatientLayout.setVerticalGroup(
            tabRemovePatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 707, Short.MAX_VALUE)
            .addGroup(tabRemovePatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabRemovePatientLayout.createSequentialGroup()
                    .addContainerGap(22, Short.MAX_VALUE)
                    .addComponent(tabApprovePatient3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(21, Short.MAX_VALUE)))
        );

        menuSecretary.addTab("Remove Patients", tabRemovePatient);

        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblApproveAccounts4.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblApproveAccounts4.setText("Approve Account Termination");

        tblTermination.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        tblTermination.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Patient ID", "Forename", "Surname", "Address", "Gender", "DOB"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTermination.setRowHeight(25);
        tblTermination.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTerminationMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tblTermination);

        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblPatientID2.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblPatientID2.setText("Patient ID:");

        txtTerminatePatientID.setEditable(false);
        txtTerminatePatientID.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        btnTerminate.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        btnTerminate.setText("TERMINATE");
        btnTerminate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTerminateActionPerformed(evt);
            }
        });

        lblPatientName2.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblPatientName2.setText("Patient Name:");

        txtTerminateName.setEditable(false);
        txtTerminateName.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        lblPatientAddress1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblPatientAddress1.setText("Address:");

        lblPatientGender1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblPatientGender1.setText("Gender");

        txtTerminateGender.setEditable(false);
        txtTerminateGender.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        txtTerminateAddress.setEditable(false);
        txtTerminateAddress.setColumns(20);
        txtTerminateAddress.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtTerminateAddress.setRows(5);
        jScrollPane9.setViewportView(txtTerminateAddress);

        lblPatientDOB1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblPatientDOB1.setText("DOB:");

        txtTerminateDOB.setEditable(false);
        txtTerminateDOB.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblPatientID2)
                    .addComponent(lblPatientAddress1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(txtTerminatePatientID, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(lblPatientName2))
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(txtTerminateName, javax.swing.GroupLayout.DEFAULT_SIZE, 613, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnTerminate)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(lblPatientGender1)
                                .addGap(18, 18, 18)
                                .addComponent(txtTerminateGender, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblPatientDOB1)))
                        .addGap(18, 18, 18)
                        .addComponent(txtTerminateDOB, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblPatientID2)
                    .addComponent(txtTerminatePatientID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPatientName2)
                    .addComponent(txtTerminateName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(lblPatientAddress1))
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblPatientGender1)
                                .addComponent(txtTerminateGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblPatientDOB1)
                                .addComponent(txtTerminateDOB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnTerminate)
                        .addGap(24, 24, 24))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblApproveAccounts4)
                .addGap(388, 388, 388))
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblApproveAccounts4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout tabApproveTerminationLayout = new javax.swing.GroupLayout(tabApproveTermination);
        tabApproveTermination.setLayout(tabApproveTerminationLayout);
        tabApproveTerminationLayout.setHorizontalGroup(
            tabApproveTerminationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabApproveTerminationLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        tabApproveTerminationLayout.setVerticalGroup(
            tabApproveTerminationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabApproveTerminationLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        menuSecretary.addTab("Approve Account Termination", tabApproveTermination);

        btnLogout.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnLogout.setText("LOGOUT");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(365, 365, 365)
                .addComponent(lblMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLogout)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addComponent(menuSecretary, javax.swing.GroupLayout.PREFERRED_SIZE, 1087, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLogout))
                .addGap(24, 24, 24)
                .addComponent(menuSecretary)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblPatientRequestsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPatientRequestsMouseClicked
        int row = 0;
        String[] data = new String[5];
        for(int i = 0; i < data.length; i++)
        {
            row = this.tblPatientRequests.getSelectedRow();
            String value = tblPatientRequests.getModel().getValueAt(row, i).toString();
            data[i] = value;
        }
        this.txtPatientForename.setText(data[0]);
        this.txtPatientSurname.setText(data[1]);
        this.txtPatientAddress.setText(data[2]);
        this.txtPatientGender.setText(data[3]);
        this.txtPatientDOB.setText(data[4]);
    }//GEN-LAST:event_tblPatientRequestsMouseClicked

    private void btnApprovePatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApprovePatientActionPerformed
        String id = String.format("P%03d", Patient.patients.length + 1);
        String forename = this.txtPatientForename.getText();
        String surname = this.txtPatientSurname.getText();
        String address = this.txtPatientAddress.getText();
        String gender = this.txtPatientGender.getText();
        String dob = this.txtPatientDOB.getText();
        
        for(AccountRequest accountRequest : AccountRequest.accountRequests)
        {
            if((accountRequest.getForename().equals(forename)) && (accountRequest.getSurname().equals(surname)))
            {
                int confirm = JOptionPane.showConfirmDialog(this, "ARE YOU SURE YOU WANT TO APPROVE?", "CONFIRM", 
                JOptionPane.YES_NO_OPTION);
        
                if(confirm == 0)
                {
                    Patient newPatient = new Patient(id, accountRequest.getPassword(), forename, surname, address, gender, dob);
                    newPatient.addPatient(newPatient);
                    
                    accountRequest.removeAccountRequest(accountRequest);
                    
                    DefaultTableModel model = (DefaultTableModel) this.tblPatientRequests.getModel();
                    int rows = model.getRowCount();
                    
                    if(rows > 0)
                    {
                        for (int i = rows - 1; i >= 0; i--)
                        {
                            model.removeRow(i);
                        }
                    }
                    
                    this.txtPatientForename.setText("");
                    this.txtPatientSurname.setText("");
                    this.txtPatientAddress.setText("");
                    this.txtPatientGender.setText("");
                    this.txtPatientDOB.setText("");
                    
                    setAccountRequests();
                    setPatients();
                }
                break;
            }
        }
        
        
    }//GEN-LAST:event_btnApprovePatientActionPerformed

    private void tblAppointmentRequestsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAppointmentRequestsMouseClicked
        int row = 0;
        String[] data = new String[5];
        for(int i = 0; i < data.length; i++)
        {
            row = this.tblAppointmentRequests.getSelectedRow();
            String value = tblAppointmentRequests.getModel().getValueAt(row, i).toString();
            data[i] = value;
        }
        this.txtAppointmentPatientID.setText(data[0]);
        this.txtAppointmentPatient.setText(data[1]);
        this.txtAppointmentDoctorID.setText(data[2]);
        this.txtAppointmentDoctor.setText(data[3]);
        this.txtAppointmentDate.setText(data[4]);
    }//GEN-LAST:event_tblAppointmentRequestsMouseClicked

    private void btnApproveAppointmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApproveAppointmentActionPerformed
        String doctorID = this.txtAppointmentDoctorID.getText();
        String patientID = this.txtAppointmentPatientID.getText();
        String date = this.txtAppointmentDate.getText();
        
        int confirm = JOptionPane.showConfirmDialog(this, "ARE YOU SURE YOU WANT TO APPROVE?", "CONFIRM", 
                JOptionPane.YES_NO_OPTION);
        
        if(confirm == 0)
        {
            for(Patient patient : Patient.patients)
            {
                if(patient.getID().equals(patientID))
                {
                    for(Doctor doctor : Doctor.doctors)
                    {
                        if(doctor.getID().equals(doctorID))
                        {
                            Appointment newAppointment = new Appointment(doctor, patient, date);
                            newAppointment.addAppointment(newAppointment);
                            
                            for(AppointmentRequest appointmentRequest : AppointmentRequest.appointmentRequests)
                            {
                                if((appointmentRequest.getDoctor().getID().equals(doctor.getID())) &&
                                        (appointmentRequest.getPatient().getID().equals(patient.getID())))
                                {
                                    appointmentRequest.removeAppointmentRequest(appointmentRequest);
                                }
                            }
                        }
                    }
                }
            }
            
                    
            int selectedRow = this.tblAppointmentRequests.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) this.tblAppointmentRequests.getModel();
            model.removeRow(selectedRow);
        }
    }//GEN-LAST:event_btnApproveAppointmentActionPerformed

    private void btnDeclineAppointmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeclineAppointmentActionPerformed
        
        
        int confirm = JOptionPane.showConfirmDialog(this, "ARE YOU SURE YOU WANT TO DECLINE?", "CONFIRM", 
                JOptionPane.YES_NO_OPTION);
        
        if(confirm == 0)
        {   
            int selectedRow = this.tblAppointmentRequests.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) this.tblAppointmentRequests.getModel();
            model.removeRow(selectedRow);
            
            String doctorID = this.txtAppointmentDoctorID.getText();
            String patientID = this.txtAppointmentPatientID.getText();
            
            //REMOVE APPOINTMENT FROM REQUEST ARRAY
            for(AppointmentRequest appointmentRequest : AppointmentRequest.appointmentRequests)
            {
                Doctor doctor = appointmentRequest.getDoctor();
                Patient patient = appointmentRequest.getPatient();
                
                if((doctor.getID().equals(doctorID)) && (patient.getID().equals(patientID)))
                {
                    appointmentRequest.removeAppointmentRequest(appointmentRequest);
                }
            }
        }
    }//GEN-LAST:event_btnDeclineAppointmentActionPerformed

    private void tblPrescriptionsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPrescriptionsMouseClicked
        int row = 0;
        String[] data = new String[5];
        for(int i = 0; i < data.length; i++)
        {
            row = this.tblPrescriptions.getSelectedRow();
            String value = tblPrescriptions.getModel().getValueAt(row, i).toString();
            data[i] = value;
        }
        this.txtPrescriptionDoctorID.setText(data[0]);
        this.txtPrescriptionPatientID.setText(data[1]);
        this.txtPrescriptionPatient.setText(data[2]);
        this.txtPrescriptionMedicine.setText(data[3]);
        this.txtPrescriptionQuantity.setText(data[4]);
        
    }//GEN-LAST:event_tblPrescriptionsMouseClicked

    private void btnApprovePrescriptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApprovePrescriptionActionPerformed
       int confirm = JOptionPane.showConfirmDialog(this, "ARE YOU SURE YOU WANT TO APPROVE THIS PRESCRIPTION?", "CONFIRM", 
                JOptionPane.YES_NO_OPTION);
        
        if(confirm == 0)
        {
            String doctorID = this.txtPrescriptionDoctorID.getText();
            String patientID = this.txtPrescriptionPatientID.getText();
            for(PrescriptionRequest prescriptionRequest : PrescriptionRequest.prescriptionRequests)
            {
                Doctor doctor = prescriptionRequest.getDoctor();
                Patient patient = prescriptionRequest.getPatient();
                
                if((doctor.getID().equals(doctorID)) && (patient.getID().equals(patientID)))
                {
                    System.out.println(prescriptionRequest.getMedicine().getName());
                    
                    for(Medicine medicine : Medicine.medicines)
                    {
                        if(medicine.getName().equals(prescriptionRequest.getMedicine().getName()))
                        {
                            System.out.println("TRUE");
                            int stockAmount = medicine.getStock();
                            int quantity = prescriptionRequest.getQuantity();
                            int newStock = stockAmount - quantity;
                            
                            if((newStock) < 0)
                            {
                                int orderMore = JOptionPane.showConfirmDialog(this, "THERE IS NOT ENOUGH MEDICINE IN STOCK TO FULFILL THIS PRESCRIPTION"
                                    + "\nWOULD YOU LIKE TO ORDER MORE?", "CONFIRM", JOptionPane.YES_NO_OPTION);
                                if(orderMore == 0)
                                {
                                    //OPEN ORDER STOCK TAB
                                    this.txtMedicineName.setText(medicine.getName());
                                    this.txtMedicineQuantity.setText(Integer.toString(medicine.getStock()));
                                    this.menuSecretary.setSelectedIndex(4);
                                }
                                break;
                            }
                            else
                            {
                                medicine.setStock(newStock);
                                for(Prescription prescription : Prescription.prescriptions)
                                {
                                    if((prescription.getDoctor() == prescriptionRequest.getDoctor()) && 
                                            prescription.getPatient() == prescriptionRequest.getPatient())
                                    {
                                        prescription.removePrescription(prescription);
                                    }
                                }
                                
                                Prescription newPrescription = new Prescription(doctor, patient, prescriptionRequest.getNotes(),
                                    medicine, quantity, prescriptionRequest.getDosage());
                                
                                newPrescription.addPrescription(newPrescription);
                                prescriptionRequest.removePrescriptionRequest(prescriptionRequest);
                                
                                this.txtPrescriptionDoctorID.setText("");
                                this.txtPrescriptionMedicine.setText("");
                                this.txtPrescriptionPatient.setText("");
                                this.txtPrescriptionPatientID.setText("");
                                this.txtPrescriptionQuantity.setText("");
                                
                                Medicine.saveMedicine();
                                
                                setPrescriptions();
                                setMedicine();
                                break;
                            } 
                        }
                    }
                    break;
                }
            } 
        }
    }//GEN-LAST:event_btnApprovePrescriptionActionPerformed

    private void btnOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrderActionPerformed
        String name = this.txtMedicineName.getText();
        int quantity = 0;
        boolean error = false;
        try
        {
            quantity = Integer.parseInt(this.txtOrderItems.getText());
        }
        catch(NumberFormatException err)
        {
            JOptionPane.showMessageDialog(this, "ENTER ONLY INTEGERS!", "ERROR", JOptionPane.ERROR_MESSAGE);
            error = true;
        }
        if(error != true)
        {
            int confirm = JOptionPane.showConfirmDialog(this, "ARE YOU SURE YOU WANT TO ORDER " + quantity + " ITEMS OF " + name, "CONFIRM", 
            JOptionPane.YES_NO_OPTION);
            
            if(confirm == 0)
            {
                orderStock(name, quantity);
            }
        }  
        
        this.txtMedicineName.setText("");
        this.txtMedicineQuantity.setText("");
        this.txtOrderItems.setText("");
    }//GEN-LAST:event_btnOrderActionPerformed

    private void tblStockMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblStockMouseClicked
        int row = 0;
        String[] data = new String[2];
        for(int i = 0; i < data.length; i++)
        {
            row = this.tblStock.getSelectedRow();
            String value = tblStock.getModel().getValueAt(row, i).toString();
            data[i] = value;
        }
        this.txtMedicineName.setText(data[0]);
        this.txtMedicineQuantity.setText(data[1]);
    }//GEN-LAST:event_tblStockMouseClicked

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "ARE YOU SURE YOU WANT TO REMOVE THIS PATIENT ACCOUNT?", "CONFIRM", 
                JOptionPane.YES_NO_OPTION);
        
        if(confirm == 0)
        {
            for(Patient patient : Patient.patients)
            {
                if(patient.getID().equals(this.txtID.getText()))
                {
                    this.txtID.setText("");
                    this.txtName.setText("");
                    this.txtAddress.setText("");
                    this.txtGender.setText("");
                    this.txtDOB.setText("");
                    
                    patient.removePatient(patient);
                    setPatients();
                }
            } 
        }
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void tblPatientsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPatientsMouseClicked
        int row = 0;
        String[] data = new String[6];
        for(int i = 0; i < data.length; i++)
        {
            row = this.tblPatients.getSelectedRow();
            String value = tblPatients.getModel().getValueAt(row, i).toString();
            data[i] = value;
        }
        this.txtID.setText(data[0]);
        this.txtName.setText(data[1] + " " + data[2]);
        this.txtAddress.setText(data[3]);
        this.txtGender.setText(data[4]);
        this.txtDOB.setText(data[5]);
    }//GEN-LAST:event_tblPatientsMouseClicked

    private void btnTerminateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTerminateActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "ARE YOU SURE YOU WANT TO REMOVE THIS PATIENT ACCOUNT?", "CONFIRM", 
                JOptionPane.YES_NO_OPTION);
        
        if(confirm == 0)
        {
            String id = this.txtTerminatePatientID.getText();
            for(Patient patient : Patient.patients)
            {
                if(patient.getID().equals(id))
                {
                    this.txtTerminatePatientID.setText("");
                    this.txtTerminateName.setText("");
                    this.txtTerminateAddress.setText("");
                    this.txtTerminateGender.setText("");
                    this.txtTerminateDOB.setText("");
                    
                    patient.removePatient(patient);
                    setPatients();
                    
                    for(TerminationRequest terminationRequest : TerminationRequest.terminationRequests)
                    {
                        if(terminationRequest.getPatient().getID().equals(id))
                        {
                            terminationRequest.removeTerminationRequest(terminationRequest);
                            setTerminations();
                            break;
                        }
                    }
                    break;
                }
            }
        }
    }//GEN-LAST:event_btnTerminateActionPerformed

    private void tblTerminationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTerminationMouseClicked
        int row = 0;
        String[] data = new String[6];
        for(int i = 0; i < data.length; i++)
        {
            row = this.tblTermination.getSelectedRow();
            String value = tblTermination.getModel().getValueAt(row, i).toString();
            data[i] = value;
        }
        this.txtTerminatePatientID.setText(data[0]);
        this.txtTerminateName.setText(data[1] + " " + data[2]);
        this.txtTerminateAddress.setText(data[3]);
        this.txtTerminateGender.setText(data[4]);
        this.txtTerminateDOB.setText(data[5]);
    }//GEN-LAST:event_tblTerminationMouseClicked

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you wish to logout?", "WARNING", JOptionPane.WARNING_MESSAGE);

        if(confirm == 0)
        {
            User.loggedUser = null;

            User.saveUsers();

            new Login().setVisible(true);

            this.setVisible(false);
        }
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void tblMedicineRequestMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMedicineRequestMouseClicked
        int row = 0;
        String[] data = new String[2];
        for(int i = 0; i < data.length; i++)
        {
            row = this.tblMedicineRequest.getSelectedRow();
            String value = tblMedicineRequest.getModel().getValueAt(row, i).toString();
            data[i] = value;
        }
        this.txtMedicineName.setText(data[0]);
        this.txtMedicineQuantity.setText(data[1]);
        this.txtOrderItems.setText(data[1]);
    }//GEN-LAST:event_tblMedicineRequestMouseClicked

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
            java.util.logging.Logger.getLogger(SecretaryMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SecretaryMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SecretaryMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SecretaryMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SecretaryMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApproveAppointment;
    private javax.swing.JButton btnApprovePatient;
    private javax.swing.JButton btnApprovePrescription;
    private javax.swing.JButton btnDeclineAppointment;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnOrder;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnTerminate;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel lblAccountType1;
    private javax.swing.JLabel lblAddress;
    private javax.swing.JLabel lblAddress1;
    private javax.swing.JLabel lblAddress2;
    private javax.swing.JLabel lblApproveAccounts;
    private javax.swing.JLabel lblApproveAccounts1;
    private javax.swing.JLabel lblApproveAccounts2;
    private javax.swing.JLabel lblApproveAccounts3;
    private javax.swing.JLabel lblApproveAccounts4;
    private javax.swing.JLabel lblDOB;
    private javax.swing.JLabel lblDoctorID;
    private javax.swing.JLabel lblForename;
    private javax.swing.JLabel lblForename1;
    private javax.swing.JLabel lblGender;
    private javax.swing.JLabel lblGender1;
    private java.awt.Label lblMain;
    private javax.swing.JLabel lblMedicine;
    private javax.swing.JLabel lblMedicineMain;
    private javax.swing.JLabel lblMedicineName;
    private javax.swing.JLabel lblMedicineQuantity;
    private javax.swing.JLabel lblMedicineRequests;
    private javax.swing.JLabel lblNewRequests;
    private javax.swing.JLabel lblNewRequests1;
    private javax.swing.JLabel lblNewRequests2;
    private javax.swing.JLabel lblNumItems;
    private javax.swing.JLabel lblPatientAddress;
    private javax.swing.JLabel lblPatientAddress1;
    private javax.swing.JLabel lblPatientDOB;
    private javax.swing.JLabel lblPatientDOB1;
    private javax.swing.JLabel lblPatientGender;
    private javax.swing.JLabel lblPatientGender1;
    private javax.swing.JLabel lblPatientID;
    private javax.swing.JLabel lblPatientID1;
    private javax.swing.JLabel lblPatientID2;
    private javax.swing.JLabel lblPatientName1;
    private javax.swing.JLabel lblPatientName2;
    private javax.swing.JLabel lblQuantity;
    private javax.swing.JLabel lblStock;
    private javax.swing.JLabel lblStock1;
    private javax.swing.JLabel lblSurname;
    private javax.swing.JLabel lblSurname1;
    private javax.swing.JLabel lblSurname2;
    private javax.swing.JLabel lblUserID1;
    private javax.swing.JLabel lblUserInfo1;
    private javax.swing.JLabel lblUserName1;
    private javax.swing.JLabel lblUserName2;
    private javax.swing.JTabbedPane menuSecretary;
    private javax.swing.JPanel tabApprovePatient;
    private javax.swing.JPanel tabApprovePatient1;
    private javax.swing.JPanel tabApprovePatient2;
    private javax.swing.JPanel tabApprovePatient3;
    private javax.swing.JPanel tabApproveTermination;
    private javax.swing.JPanel tabGiveMedicine;
    private javax.swing.JPanel tabOrderStock;
    private javax.swing.JPanel tabRemovePatient;
    private javax.swing.JPanel tabSetAppointment;
    private javax.swing.JPanel tabUserInfo;
    private javax.swing.JTable tblAppointmentRequests;
    private javax.swing.JTable tblMedicineRequest;
    private javax.swing.JTable tblPatientRequests;
    private javax.swing.JTable tblPatients;
    private javax.swing.JTable tblPrescriptions;
    private javax.swing.JTable tblStock;
    private javax.swing.JTable tblTermination;
    private javax.swing.JTextArea txtAddress;
    private javax.swing.JTextField txtAppointmentDate;
    private javax.swing.JTextField txtAppointmentDoctor;
    private javax.swing.JTextField txtAppointmentDoctorID;
    private javax.swing.JTextField txtAppointmentPatient;
    private javax.swing.JTextField txtAppointmentPatientID;
    private javax.swing.JTextField txtDOB;
    private javax.swing.JTextField txtGender;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtMedicineName;
    private javax.swing.JTextField txtMedicineQuantity;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtOrderItems;
    private javax.swing.JTextArea txtPatientAddress;
    private javax.swing.JTextField txtPatientDOB;
    private javax.swing.JTextField txtPatientForename;
    private javax.swing.JTextField txtPatientGender;
    private javax.swing.JTextField txtPatientSurname;
    private javax.swing.JTextField txtPrescriptionDoctorID;
    private javax.swing.JTextField txtPrescriptionMedicine;
    private javax.swing.JTextField txtPrescriptionPatient;
    private javax.swing.JTextField txtPrescriptionPatientID;
    private javax.swing.JTextField txtPrescriptionQuantity;
    private javax.swing.JTextArea txtTerminateAddress;
    private javax.swing.JTextField txtTerminateDOB;
    private javax.swing.JTextField txtTerminateGender;
    private javax.swing.JTextField txtTerminateName;
    private javax.swing.JTextField txtTerminatePatientID;
    private javax.swing.JTextField txtUserAccountType;
    private javax.swing.JTextArea txtUserAddress;
    private javax.swing.JTextField txtUserID;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables
}
