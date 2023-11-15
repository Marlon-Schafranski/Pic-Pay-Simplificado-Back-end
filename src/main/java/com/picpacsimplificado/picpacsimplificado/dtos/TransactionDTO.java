package com.picpacsimplificado.picpacsimplificado.dtos;

import java.math.BigDecimal;

//Record para criações dos dados do POST do PAYLOAd Do execicio 

// Metodo para quem envia e recebe
public record TransactionDTO(BigDecimal value, Long senderId, Long receiverId) {

}