package com.example.visitrwanda;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Booking {

    @PrimaryKey
    public int idno;

    @ColumnInfo(name = "client_name")
    public String client_name;

    @ColumnInfo(name = "contactno")
    public String contactno;

    @ColumnInfo(name = "gender")
    public String gender;

    public Booking(int idno, String client_name, String contactno, String gender) {
        this.idno = idno;
        this.client_name = client_name;
        this.contactno = contactno;
        this.gender = gender;
    }
}
