package com.alexa.store.dto;

import com.alexa.store.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponseDTO {
    private String userName;
    private String productName;
    private int rating;
    private String comment;
}
