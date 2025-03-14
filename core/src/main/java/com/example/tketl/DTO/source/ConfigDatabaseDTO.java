package com.example.tketl.DTO.source;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConfigDatabaseDTO extends BaseDTO {
    public String host;
    public int port;
    public String databaseName;

    public String user;

    public String password;

    public String interface_name;




}
