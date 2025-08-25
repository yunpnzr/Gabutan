package com.yunpznr.gabutan.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "token", schema = "users")
public class Token {
    @Id
    private UUID id;
    @NotBlank
    private String token;
    @NotBlank
    private Long expiredAt;

    @OneToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private User user;
}
