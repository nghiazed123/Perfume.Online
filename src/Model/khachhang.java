package Model;

public class khachhang {
    private String ten, gioitinh, diachi, email, ngaysinh, ngaytao, sdt;
    private int makh;

    public khachhang() {
    }

    public khachhang(String ten, String gioitinh, String diachi, String email, String ngaysinh, String ngaytao, int makh, String sdt) {
        this.ten = ten;
        this.gioitinh = gioitinh;
        this.diachi = diachi;
        this.email = email;
        this.ngaysinh = ngaysinh;
        this.ngaytao = ngaytao;
        this.makh = makh;
        this.sdt = sdt;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(String ngaytao) {
        this.ngaytao = ngaytao;
    }

    public int getMakh() {
        return makh;
    }

    public void setMakh(int makh) {
        this.makh = makh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
}
