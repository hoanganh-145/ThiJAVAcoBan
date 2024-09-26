package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
  Tác giả: Nguyễn Huỳnh Hoàng Anh
 */
public class QLKhachHang {

    private ArrayList<KhachHang> dsKhachHang;

    public QLKhachHang() {
        dsKhachHang = new ArrayList<>();
    }

    public QLKhachHang(ArrayList<KhachHang> dsKhachHang) {
        this.dsKhachHang = dsKhachHang;
    }

    public ArrayList<KhachHang> getDsKhachHang() {
        return dsKhachHang;
    }

    public void setDsKhachHang(ArrayList<KhachHang> dsKhachHang) {
        this.dsKhachHang = dsKhachHang;
    }

    //sinh viên cải đặt cho các phương thức xử lý sau
    public void DocKhachHang(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = br.readLine()) != null) {
            String[] data = line.split(";");
            if (data.length == 5) {
                String maso = data[0];
                String hoten = data[1];
                int loai = Integer.parseInt(data[2]);
                double chisocu = Double.parseDouble(data[3]);
                double chisomoi = Double.parseDouble(data[4]);
                KhachHang kh = new KhachHang(maso, hoten, loai, chisocu, chisomoi);
                dsKhachHang.add(kh);
            }
        }
        br.close();
    }

    public boolean GhiHoaDon(String filename) throws IOException{
        BufferedWriter bw = new BufferedWriter(new FileWriter(filename));

        for (KhachHang kh : dsKhachHang) {
            String line = kh.getMaso() + ";" + kh.getHoten() + ";" + kh.getTieuThu() + ";" + kh.getTienTra();
            bw.write(line);
            bw.newLine();
        }
        

        bw.close();
        return true;
    }
    
    public void sapXepTheoLoaiHinh() {
        Collections.sort(dsKhachHang, new Comparator<KhachHang>() {
            @Override
            public int compare(KhachHang o1, KhachHang o2) {
                return Integer.compare(o2.getLoai(), o1.getLoai());
            }
        });
    }

    public KhachHang getTieuThuCaoNhat() {
       if (dsKhachHang.isEmpty()) {
            return null;
        }

        KhachHang tieuThucaonhat = dsKhachHang.get(0);
        for (KhachHang kh : dsKhachHang) {
            if (kh.getTieuThu() > tieuThucaonhat.getTieuThu()) {
                tieuThucaonhat = kh;
            }
        }
        return tieuThucaonhat;
    }

//    public double getTieuThuThapNhat() {
//        return 0;
//    }

    public double getTieuThuTrungBinh() {
        if (dsKhachHang.isEmpty()) {
            return 0;
        }

        double tongTieuThu = 0;
        for (KhachHang kh : dsKhachHang) {
            tongTieuThu += kh.getTieuThu();
        }

        return tongTieuThu / dsKhachHang.size();
    }
}
