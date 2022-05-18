/* Decompiler 2ms, total 259ms, lines 26 */
package com.js.jsbapi.fileUpload.dto;

public class UploadResponse {
   private final String fileName;
   private final String fullName;
   private final String dateOfBirth;

   public UploadResponse(String fileName, String fullName, String dateOfBirth) {
      this.fileName = fileName;
      this.fullName = fullName;
      this.dateOfBirth = dateOfBirth;
   }

   public String getDateOfBirth() {
      return this.dateOfBirth;
   }

   public String getFileName() {
      return this.fileName;
   }

   public String getFullName() {
      return this.fullName;
   }
}