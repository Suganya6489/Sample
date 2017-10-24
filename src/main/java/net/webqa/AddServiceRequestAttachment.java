/**
 * AddServiceRequestAttachment.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package net.webqa;

public class AddServiceRequestAttachment  implements java.io.Serializable {
    private java.lang.String authKey;

    private int requestId;

    private java.lang.String fileName;

    private byte[] fileStream;

    public AddServiceRequestAttachment() {
    }

    public AddServiceRequestAttachment(
           java.lang.String authKey,
           int requestId,
           java.lang.String fileName,
           byte[] fileStream) {
           this.authKey = authKey;
           this.requestId = requestId;
           this.fileName = fileName;
           this.fileStream = fileStream;
    }


    /**
     * Gets the authKey value for this AddServiceRequestAttachment.
     * 
     * @return authKey
     */
    public java.lang.String getAuthKey() {
        return authKey;
    }


    /**
     * Sets the authKey value for this AddServiceRequestAttachment.
     * 
     * @param authKey
     */
    public void setAuthKey(java.lang.String authKey) {
        this.authKey = authKey;
    }


    /**
     * Gets the requestId value for this AddServiceRequestAttachment.
     * 
     * @return requestId
     */
    public int getRequestId() {
        return requestId;
    }


    /**
     * Sets the requestId value for this AddServiceRequestAttachment.
     * 
     * @param requestId
     */
    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }


    /**
     * Gets the fileName value for this AddServiceRequestAttachment.
     * 
     * @return fileName
     */
    public java.lang.String getFileName() {
        return fileName;
    }


    /**
     * Sets the fileName value for this AddServiceRequestAttachment.
     * 
     * @param fileName
     */
    public void setFileName(java.lang.String fileName) {
        this.fileName = fileName;
    }


    /**
     * Gets the fileStream value for this AddServiceRequestAttachment.
     * 
     * @return fileStream
     */
    public byte[] getFileStream() {
        return fileStream;
    }


    /**
     * Sets the fileStream value for this AddServiceRequestAttachment.
     * 
     * @param fileStream
     */
    public void setFileStream(byte[] fileStream) {
        this.fileStream = fileStream;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AddServiceRequestAttachment)) return false;
        AddServiceRequestAttachment other = (AddServiceRequestAttachment) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.authKey==null && other.getAuthKey()==null) || 
             (this.authKey!=null &&
              this.authKey.equals(other.getAuthKey()))) &&
            this.requestId == other.getRequestId() &&
            ((this.fileName==null && other.getFileName()==null) || 
             (this.fileName!=null &&
              this.fileName.equals(other.getFileName()))) &&
            ((this.fileStream==null && other.getFileStream()==null) || 
             (this.fileStream!=null &&
              java.util.Arrays.equals(this.fileStream, other.getFileStream())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getAuthKey() != null) {
            _hashCode += getAuthKey().hashCode();
        }
        _hashCode += getRequestId();
        if (getFileName() != null) {
            _hashCode += getFileName().hashCode();
        }
        if (getFileStream() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getFileStream());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getFileStream(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AddServiceRequestAttachment.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webqa.net/", ">AddServiceRequestAttachment"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("authKey");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "authKey"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requestId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "requestId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fileName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "fileName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fileStream");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "fileStream"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
