/* Decompiler 13ms, total 293ms, lines 184 */
package com.js.jsbapi.fileUpload.dto;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Service
public class FileInformation {
    String status;
    String message;
    String uuid;
    String fileName;
    String fullPath;
    String fileStoragePath;
    String httpPath;

}
