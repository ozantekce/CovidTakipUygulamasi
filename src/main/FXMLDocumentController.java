
package main;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;


public class FXMLDocumentController implements Initializable {
    
    
    @FXML
    private AnchorPane Pane;

    
    @FXML
    private Button ekle;

    @FXML
    private Button sil;

    @FXML
    private Button guncelle;

    @FXML
    private Button sorgula;

    @FXML
    private Slider atesSlider;

    @FXML
    private Label ates;

    @FXML
    private CheckBox teshis;

    @FXML
    private ComboBox<String> memleketi;

    @FXML
    private RadioButton erkek;

    @FXML
    private RadioButton kadin;

    @FXML
    private RadioButton isci;

    @FXML
    private RadioButton memur;

    @FXML
    private RadioButton emekli;

    @FXML
    private RadioButton ogrenci;

    @FXML
    private TextField hastaadi;
    
    
    @FXML
    private TableView<Kisi> table;
    
    @FXML
    private TableColumn<Kisi, String> columnHastaadi;

    @FXML
    private TableColumn<Kisi, String> columnMemleketi;

    @FXML
    private TableColumn<Kisi, String> columnMeslek;

    @FXML
    private TableColumn<Kisi, String> columnCinsiyet;

    @FXML
    private TableColumn<Kisi, String> columnAtes;

    @FXML
    private TableColumn<Kisi, String> columnTeshis;
    
    ObservableList<Kisi> kisiler = FXCollections.observableArrayList();

    
    Sehir sehirs [] = new Sehir[5];
    
    class Sehir{
        
        private String adi;
        private int vakasayisi=0;
        private double ortalamaates=0;

        public Sehir(String adi) {
            this.adi = adi;
        }
        
        public void vakaekle(String ates){
            
            
            if(ortalamaates==0)ortalamaates = Double.valueOf(ates);
            else{
                
                ortalamaates=((ortalamaates*vakasayisi)+Double.valueOf(ates))/(vakasayisi+1);
                
            }
            
            vakasayisi++;
            
        }
        
        
        
        public double getOrtalamaates() {
            return ortalamaates;
        }

        public String getAdi() {
            return adi;
        }

        public int getVakasayisi() {
            return vakasayisi;
        }
        
        
        
        
        
    }
    
    
    class Kisi{
        
        private StringProperty  adi = new SimpleStringProperty(this, "adi");
        private StringProperty atesi= new SimpleStringProperty(this, "atesi");
        private StringProperty teshis= new SimpleStringProperty(this, "teshis");
        private StringProperty memleketi= new SimpleStringProperty(this, "memleketi");
        private StringProperty cinsiyeti= new SimpleStringProperty(this, "cinsiyeti");
        private StringProperty meslegi= new SimpleStringProperty(this, "meslegi");

        public Kisi(String adi, String atesi, String teshis, String memleketi, String cinsiyeti, String meslegi) {
            this.adi.set(adi);
            this.atesi.set(atesi);
            this.teshis.set(teshis);
            this.memleketi.set(memleketi);
            this.cinsiyeti.set(cinsiyeti);
            this.meslegi.set(meslegi);
        }

        public String stringgetAdi() {
            return adi.get();
        }

        public String stringgetAtesi() {
            return atesi.get();
        }

        public String stringgetCinsiyeti() {
            return cinsiyeti.get();
        }

        public String stringgetMemleketi() {
            return memleketi.get();
        }

        public String stringgetMeslegi() {
            return meslegi.get();
        }

        public String stringgetTeshis() {
            return teshis.get();
        }

        public StringProperty getAdi() {
            return adi;
        }

        public StringProperty getAtesi() {
            return atesi;
        }

        public StringProperty getCinsiyeti() {
            return cinsiyeti;
        }

        public StringProperty getMemleketi() {
            return memleketi;
        }

        public StringProperty getMeslegi() {
            return meslegi;
        }

        public StringProperty getTeshis() {
            return teshis;
        }
        
        
        
        
        
        
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        sehirs[0]=new Sehir("Ankara");
        sehirs[1]=new Sehir("İstanbul");
        sehirs[2]=new Sehir("İzmir");
        sehirs[3]=new Sehir("Hatay");
        sehirs[4]=new Sehir("Antalya");
        
        memleketi.setEditable(false);
        memleketi.getItems().removeAll(memleketi.getItems());
        for (int i = 0; i < sehirs.length; i++) {
            
            memleketi.getItems().add(sehirs[i].getAdi());
            
        }
        memleketi.getSelectionModel().select("Ankara");
        
        
        columnAtes.setCellValueFactory(cellData -> cellData.getValue().getAtesi());
        columnCinsiyet.setCellValueFactory(cellData -> cellData.getValue().getCinsiyeti());
        columnHastaadi.setCellValueFactory(cellData -> cellData.getValue().getAdi());
        columnMemleketi.setCellValueFactory(cellData -> cellData.getValue().getMemleketi());
        columnMeslek.setCellValueFactory(cellData -> cellData.getValue().getMeslegi());
        columnTeshis.setCellValueFactory(cellData -> cellData.getValue().getTeshis());
        
        table.setItems(kisiler);
        

    }    
    
    
    @FXML
    void atesiGuncelle(MouseEvent event) {
        try {
            ates.setText(String.valueOf(atesSlider.getValue()).substring(0, 4));
        } catch (Exception e) {
            
            ates.setText(String.valueOf(atesSlider.getValue()));
            
        }
        
        
    }
    
    
        @FXML
    void cinsiyetguncelle(MouseEvent event) {
        
        
            if (event.getSource()==erkek) {
                kadin.setSelected(false);
                
            }
            else{
                erkek.setSelected(false);
            }
        
    }
    
    
    
        @FXML
    void meslegiguncelle(MouseEvent event) {

        
            if (event.getSource()==isci) {
                
                memur.setSelected(false);
                emekli.setSelected(false);
                ogrenci.setSelected(false);
                
                
                
            }
            else if(event.getSource()==memur){
                
                isci.setSelected(false);
                emekli.setSelected(false);
                ogrenci.setSelected(false);
                
            }
            else if(event.getSource()==emekli){
                isci.setSelected(false);
                memur.setSelected(false);
                ogrenci.setSelected(false);

            }
            else{
                isci.setSelected(false);
                memur.setSelected(false);
                emekli.setSelected(false);

            }
        
        
    }
    

    @FXML
    void tableekle(ActionEvent event) {
        
        
        if(hastaadi.getText().isEmpty()){
            new Alert(Alert.AlertType.NONE, "Lütfen hasta adı girin", new ButtonType("tamam")).showAndWait();
            return;
        }
        
        String t;
        if(teshis.isSelected())t="evet";
        else t="hayır";
        
        String c;
        if(erkek.isSelected())c="erkek";
        else if(kadin.isSelected())  c="kadın";
        else {
            new Alert(Alert.AlertType.NONE, "Lütfen cinsiyet seçimi yapın", new ButtonType("tamam")).showAndWait();
            return;
        }
        
        String m;
        if(isci.isSelected())m="isci";
        else if(memur.isSelected())  m="memur";
        else if(emekli.isSelected())  m="emekli";
        else if(ogrenci.isSelected())  m="ogrenci";
        else {
            new Alert(Alert.AlertType.NONE, "Lütfen meslek seçimi yapın", new ButtonType("tamam")).showAndWait();
            return;
        }
        
        
        
        kisiler.add(new Kisi(hastaadi.getText(), ates.getText(), t, memleketi.getValue(), c, m));
        
        if(memleketi.getValue().equals("Ankara"))sehirs[0].vakaekle(ates.getText());
        else if(memleketi.getValue().equals("İstanbul"))sehirs[1].vakaekle(ates.getText());
        else if(memleketi.getValue().equals("İzmir"))sehirs[2].vakaekle(ates.getText());
        else if(memleketi.getValue().equals("Hatay"))sehirs[3].vakaekle(ates.getText());
        else if(memleketi.getValue().equals("Antalya"))sehirs[4].vakaekle(ates.getText());
        
        
        new Alert(Alert.AlertType.CONFIRMATION , "Ekleme başarılı", new ButtonType("tamam")).showAndWait();
        
    }

    
    @FXML
    void tablesil(ActionEvent event) {
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setHeaderText("Emin misiniz ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
                    try {
            kisiler.remove(table.getFocusModel().getFocusedIndex());
        } catch (Exception e) {
            
        }
                    
        } else {
            
        }

        
    }
    
    @FXML
    void guncelle(ActionEvent event) {
        
        int f= table.getFocusModel().getFocusedIndex();
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setHeaderText("Emin misiniz ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
        if(hastaadi.getText().isEmpty()){
            new Alert(Alert.AlertType.NONE, "Lütfen hasta adı girin", new ButtonType("tamam")).showAndWait();
            return;
        }
        
        String t;
        if(teshis.isSelected())t="evet";
        else t="hayır";
        
        String c;
        if(erkek.isSelected())c="erkek";
        else if(kadin.isSelected())  c="kadın";
        else {
            new Alert(Alert.AlertType.NONE, "Lütfen cinsiyet seçimi yapın", new ButtonType("tamam")).showAndWait();
            return;
        }
        
        String m;
        if(isci.isSelected())m="isci";
        else if(memur.isSelected())  m="memur";
        else if(emekli.isSelected())  m="emekli";
        else if(ogrenci.isSelected())  m="ogrenci";
        else {
            new Alert(Alert.AlertType.NONE, "Lütfen meslek seçimi yapın", new ButtonType("tamam")).showAndWait();
            return;
        }
        
        
        
        kisiler.set(table.getFocusModel().getFocusedIndex(),new Kisi(hastaadi.getText(), ates.getText(), t, memleketi.getValue(), c, m));
        
        new Alert(Alert.AlertType.CONFIRMATION , "Güncelleme başarılı", new ButtonType("tamam")).showAndWait();
            table.getFocusModel().focus(f);
        
        } else {
            
            table.getFocusModel().focus(f);
            
        }
    }
    
    
    
    
    @FXML
    void guncelkisi(MouseEvent event) {
        try {
            hastaadi.setText(kisiler.get(table.getFocusModel().getFocusedIndex()).getAdi().getValue());
            
            atesSlider.setValue(Double.valueOf(kisiler.get(table.getFocusModel().getFocusedIndex()).getAtesi().getValue()));
            
            if(kisiler.get(table.getFocusModel().getFocusedIndex()).getTeshis().getValue().equals("evet"))
            teshis.setSelected(true);
            else
            teshis.setSelected(false);    
            
            memleketi.getSelectionModel().select(kisiler.get(table.getFocusModel().getFocusedIndex()).getMemleketi().getValue());
            
            if(kisiler.get(table.getFocusModel().getFocusedIndex()).getCinsiyeti().getValue().equals("erkek")){
                
                erkek.setSelected(true);
                kadin.setSelected(false);
                
            }
            else{
                
                erkek.setSelected(false);
                kadin.setSelected(true);
                
            }
            
            String tm = kisiler.get(table.getFocusModel().getFocusedIndex()).getMeslegi().getValue();
            
            if(tm.equals("isci")){
                
                isci.setSelected(true);
                memur.setSelected(false);
                emekli.setSelected(false);
                ogrenci.setSelected(false);
                
            }
            else if(tm.equals("memur")){
                
                isci.setSelected(false);
                memur.setSelected(true);
                emekli.setSelected(false);
                ogrenci.setSelected(false);
                
            }
            else if(tm.equals("emekli")){
                
                isci.setSelected(false);
                memur.setSelected(false);
                emekli.setSelected(true);
                ogrenci.setSelected(false);
                
            }
            else if(tm.equals("ogrenci")){
                
                isci.setSelected(false);
                memur.setSelected(false);
                emekli.setSelected(false);
                ogrenci.setSelected(true);
                
            }
            
            
            
        } catch (Exception e) {
        }
        
        
        

    }
    
    
    @FXML
    void sorgula(ActionEvent event) {
        
        String s="";
        
        if(memleketi.getValue().equals("Ankara")){
            s+="Ankarada Vaka sayısı=";
            s+=sehirs[0].getVakasayisi();
            s+=" Ortalama ateş=";
            s+=sehirs[0].getOrtalamaates();
        }
        else if(memleketi.getValue().equals("İstanbul")){
            s+="İstanbulda Vaka sayısı=";
            s+=sehirs[1].getVakasayisi();
            s+=" Ortalama ateş=";
            s+=sehirs[1].getOrtalamaates();
        }
        else if(memleketi.getValue().equals("İzmir")){
            s+="İzmirde Vaka sayısı=";
            s+=sehirs[2].getVakasayisi();
            s+=" Ortalama ateş=";
            s+=sehirs[2].getOrtalamaates();
        }
        else if(memleketi.getValue().equals("Hatay")){
            s+="Hatayda Vaka sayısı=";
            s+=sehirs[3].getVakasayisi();
            s+=" Ortalama ateş=";
            s+=sehirs[3].getOrtalamaates();
        }
        else if(memleketi.getValue().equals("Antalya")){
            s+="Antalyada Vaka sayısı=";
            s+=sehirs[4].getVakasayisi();
            s+=" Ortalama ateş=";
            s+=sehirs[4].getOrtalamaates();
        }
        
        
        new Alert(Alert.AlertType.NONE, s , new ButtonType("tamam")).showAndWait();
        
        
    }
    
    
}
