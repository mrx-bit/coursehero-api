package kz.iitu.hackday.coursehero.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Authorization details about User")
public class UserCredential {

    @ApiModelProperty(notes = "The user email")
    private String email;
    @ApiModelProperty(notes = "The user password")
    private String password;
}

