/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package packageHotel;

import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

/**
 * Kelas Hotel merepresentasikan sebuah hotel dengan nama dan alamat.
 * 
 * @author Reza1290
 */
public class Hotel {

    private String namaHotel;
    private String alamat;
    private List<Room> daftarKamar;
    private List<Pemesanan> bookedRooms;

    /**
     * Konstruktor untuk membuat objek Hotel dengan nama hotel.
     *
     * @param hotelName Nama hotel.
     */
    public Hotel(String hotelName) {

        this.namaHotel = hotelName;
        this.alamat = "";
        this.daftarKamar = new LinkedList<Room>();
        this.bookedRooms = new LinkedList<Pemesanan>();
    }

    /**
     * Konstruktor untuk membuat objek Hotel dengan nama hotel dan alamat.
     *
     *
     * @param hotelName Nama hotel.
     * @param alamat Alamat hotel.
     */
    public Hotel(String hotelName, String alamat) {

        this.namaHotel = hotelName;
        this.alamat = alamat;
        this.daftarKamar = new LinkedList<Room>();
        this.bookedRooms = new LinkedList<Pemesanan>();

    }

    /**
     * Menambahkan Kamar, Mengisi detail kamar
     *
     * @param noKamar No Id Kamar
     * @param tipe Tipe Kamar ["king","queen","etc"]
     * @param price Harga Range 0 >=
     * @return True Jika berhasil
     */
    public boolean tambahKamar(int noKamar, String tipe, double price) {
        return tambahKamar(new Room(noKamar, tipe, price));
    }

    /**
     * Tambah Kamar Using Room Object From another method
     *
     * @param room Objek ROOM
     * @return True jika Berhasil
     */
    public boolean tambahKamar(Room room) {
        return daftarKamar.add(room);
    }
    
    /**
     * :>
     */
    public void clearKamar(){
        this.daftarKamar.clear();
    }
    
    public void clearBooked(){
        this.bookedRooms.clear();
    }
    
    /**
     * Memeriksa status pemesanan kamar.
     *
     * @param room Objek Room yang akan diperiksa status pemesanannya.
     * @return true jika kamar sudah dipesan (status = true), false jika belum
     * dipesan (status = false).
     */
    public boolean isRoomBooked(Room room) {
        return room.isStatus();
    }

    /**
     * Memesan kamar dengan menggunakan objek Pemesanan.
     *
     * @param book Objek Pemesanan yang mewakili pemesanan kamar yang ingin
     * dilakukan.
     * @return true jika pemesanan berhasil dilakukan, false jika gagal (kamar
     * sudah dipesan).
     */
    public boolean pesanKamar(Pemesanan book) {
        if (!this.isRoomBooked(book.getRoom())) {
            
            book.getRoom().setStatus(true);
            this.bookedRooms.add(book);
            return true;
        }
        return false;
    }

    /**
     * Membuat pemesanan kamar oleh seorang tamu dengan detail tertentu.
     *
     * @param detailTamu Objek Tamu yang berisi informasi tentang tamu yang
     * memesan kamar.
     * @param room Objek Room yang mewakili kamar yang ingin dipesan.
     * @param dateCheckIn Tanggal check-in yang diinginkan oleh tamu.
     * @param dateCheckOut Tanggal check-out yang diinginkan oleh tamu.
     * @return true jika pemesanan berhasil dilakukan, false jika gagal.
     */
    public boolean pesanKamar(Tamu detailTamu, Room room, LocalDate dateCheckIn, LocalDate dateCheckOut) {
        if (!this.isRoomBooked(room) && dateCheckIn.isBefore(dateCheckOut)) {
            Pemesanan booked = new Pemesanan(detailTamu, LocalDate.now(), dateCheckOut, room);
            booked.getRoom().setStatus(true); // Make it Booked

            this.bookedRooms.add(booked);
            System.out.println(this.toString());
            return true;
        }
        return false;
    }

    /**
     * Mengambil detail pemesanan untuk kamar dengan nomor tertentu.
     *
     * @param noKamar Nomor kamar yang ingin dicari dalam pemesanan.
     * @return Objek Pemesanan yang mewakili detail pemesanan kamar yang dicari.
     * Jika tidak ada pemesanan yang cocok atau kamar tidak tersedia, maka null
     * dikembalikan
     *
     */
    public Pemesanan getBookedDetails(int noKamar) {

        for (int i = 0; i < bookedRooms.size(); i++) {
            if (bookedRooms.get(i).getRoom().getNomorKamar() == noKamar && bookedRooms.get(i).getRoom().isStatus()) {
                return bookedRooms.get(i);
            }
        }
        return null;
    }

    /**
     * Menghapus pemesanan kamar berdasarkan nomor kamar.
     *
     * @param noKamar Nomor kamar yang akan dihapus dari daftar pemesanan.
     * @return true jika pemesanan berhasil dihapus, false jika tidak ada
     * pemesanan dengan nomor kamar tersebut.
     */
    public boolean removeBookedRoom(int noKamar) {
        for (int i = 0; i < bookedRooms.size(); i++) {
            if (bookedRooms.get(i).getRoom().getNomorKamar() == noKamar && bookedRooms.get(i).getRoom().isStatus()) {
                bookedRooms.get(i).getRoom().setStatus(false);
                bookedRooms.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Mendapatkan Details dari Kamar Status, NoKamar, dsb
     *
     * @param noKamar
     * @return detail Kamar
     */
    public Room getDetailsKamar(int noKamar) {
        for (int i = 0; i < daftarKamar.size(); i++) {
            if (daftarKamar.get(i).getNomorKamar() == noKamar && daftarKamar.get(i).isStatus()) {
                return daftarKamar.get(i);
            }
        }
        return null;
    }

    /**
     * Mendapatkan Seluruh Kamar
     *
     * @return Array Kamar
     */
    public List<Room> getDaftarKamar() {
        return daftarKamar;
    }

    /**
     * Mendapatkan nama hotel.
     *
     * @return Nama hotel.
     */
    public String getNamaHotel() {
        return namaHotel;
    }

    /**
     * Mengatur nama hotel.
     *
     * @param namaHotel Nama hotel yang akan diatur.
     */
    public void setNamaHotel(String namaHotel) {
        this.namaHotel = namaHotel;
    }

    /**
     * Mendapatkan alamat hotel.
     *
     * @return Alamat hotel.
     */
    public String getAlamat() {
        return alamat;
    }

    /**
     * Mengatur alamat hotel.
     *
     * @param alamat Alamat hotel yang akan diatur.
     */
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    /**
     * Representasi string dari objek Hotel.
     *
     * @return String yang berisi informasi tentang objek Hotel.
     */
    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        String str = "Hotel" + namaHotel + "\n" + "\n";
        for (int i = 0; i < bookedRooms.size(); i++) {
            Pemesanan p = bookedRooms.get(i);
            str = str + LocalTime.now().format(dtf) + " : ";
            str = str + p.getTamu().getNama() + ", " + p.getTamu().getEmail() + "\nNo. ";
            str = str + p.getRoom().getNomorKamar() + ", In=";
            str = str + p.getTanggalCheckIn() + ", Out=";
            str = str + p.getTanggalCheckOut() + "\n" + "\n";

        }

        return str;
    }

}
