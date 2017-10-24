/**
 * AddServiceRequestNoteResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package net.webqa;

public class AddServiceRequestNoteResponse  implements java.io.Serializable {
    private java.lang.String addServiceRequestNoteResult;

    public AddServiceRequestNoteResponse() {
    }

    public AddServiceRequestNoteResponse(
           java.lang.String addServiceRequestNoteResult) {
           this.addServiceRequestNoteResult = addServiceRequestNoteResult;
    }


    /**
     * Gets the addServiceRequestNoteResult value for this AddServiceRequestNoteResponse.
     * 
     * @return addServiceRequestNoteResult
     */
    public java.lang.String getAddServiceRequestNoteResult() {
        return addServiceRequestNoteResult;
    }


    /**
     * Sets the addServiceRequestNoteResult value for this AddServiceRequestNoteResponse.
     * 
     * @param addServiceRequestNoteResult
     */
    public void setAddServiceRequestNoteResult(java.lang.String addServiceRequestNoteResult) {
        this.addServiceRequestNoteResult = addServiceRequestNoteResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AddServiceRequestNoteResponse)) return false;
        AddServiceRequestNoteResponse other = (AddServiceRequestNoteResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.addServiceRequestNoteResult==null && other.getAddServiceRequestNoteResult()==null) || 
             (this.addServiceRequestNoteResult!=null &&
              this.addServiceRequestNoteResult.equals(other.getAddServiceRequestNoteResult())));
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
        if (getAddServiceRequestNoteResult() != null) {
            _hashCode += getAddServiceRequestNoteResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AddServiceRequestNoteResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webqa.net/", ">AddServiceRequestNoteResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addServiceRequestNoteResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "AddServiceRequestNoteResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
