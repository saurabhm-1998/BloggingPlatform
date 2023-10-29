package com.example.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FollowDto {
    @NotNull(message = "enter user id")
    private int followerId;
    @NotNull(message = "enter user id")
    private int followingId;
}
