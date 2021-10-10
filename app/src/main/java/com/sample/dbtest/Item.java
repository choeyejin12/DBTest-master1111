package com.sample.dbtest;
//데이터베이스에서 가져올 정보들을 정의함
public class Item {

    int Place_id; //int 형으로 정의
    String P_name;
    String P_address;
    String P_image;

    public Item() {
    }

    public Item(int Place_id, String P_name, String P_address, String P_image) {
        this.Place_id = Place_id; //this를 사용하여 대치(Place_id자체는 많기 때문)
        this.P_name = P_name;
        this.P_address = P_address;
        this.P_image = P_image;
    }

    //Placed_id 등등의 변수를 데이터베이스에서 가져오는 함수를 만든것(get은 가져오고 set으 대입)
    public int getPlace_id() {
        return Place_id;
    }

    public void setPlace_id(int Place_id) {
        this.Place_id = Place_id;
    }

    public String getP_name() {
        return P_name;
    }

    public void setP_name(String P_name) {
        this.P_name = P_name;
    }

    public String getP_address() {
        return P_address;
    }

    public void setP_address(String P_address) {
        this.P_address = P_address;
    }

    public String getP_image() {
        return P_image;
    }

    public void setP_image(String P_image) {
        this.P_image = P_image;
    }

}
