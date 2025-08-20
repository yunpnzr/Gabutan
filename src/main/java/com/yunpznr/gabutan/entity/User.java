package com.yunpznr.gabutan.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.AccessType;

import java.math.BigInteger;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user", schema = "users")
public class User {
    @Id
    private UUID id;

    //@UniqueElements(message = "Username tidak tersedia")
    private String username;

    @NotBlank(message = "Nama gak boleh kosong")
    private String name;

    @NotBlank(message = "Email tidak boleh kosong")
    @Email(message = "Format email tidak valid")
    //@UniqueElements(message = "Email sudah digunakan")
    private String email;

    @NotBlank(message = "Password gak boleh kosong")
    private String password;

    private String token;

    private BigInteger tokenExpiredAt;

    @Column(name = "is_validated", nullable = false)
    private boolean validated;
}
