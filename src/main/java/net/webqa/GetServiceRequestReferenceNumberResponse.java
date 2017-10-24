/**
 * GetServiceRequestReferenceNumberResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package net.webqa;

public class GetServiceRequestReferenceNumberResponse  implements java.io.Serializable {
    private java.lang.String getServiceRequestReferenceNumberResult;

    public GetServiceRequestReferenceNumberResponse() {
    }

    public GetServiceRequestReferenceNumberResponse(
           java.lang.String getServiceRequestReferenceNumberResult) {
           this.getServiceRequestReferenceNumberResult = getServiceRequestReferenceNumberResult;
    }


    /**
     * Gets the getServiceRequestReferenceNumberResult value for this GetServiceRequestReferenceNumberResponse.
     * 
     * @return getServiceRequestReferenceNumberResult
     */
    public java.lang.String getGetServiceRequestReferenceNumberResult() {
        return getServiceRequestReferenceNumberResult;
    }


    /**
     * Sets the getServiceRequestReferenceNumberResult value for this GetServiceRequestReferenceNumberResponse.
     * 
     * @param getServiceRequestReferenceNumberResult
     */
    public void setGetServiceRequestReferenceNumberResult(java.lang.String getServiceRequestReferenceNumberResult) {
        this.getServiceRequestReferenceNumberResult = getServiceRequestReferenceNumberResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetServiceRequestReferenceNumberResponse)) return false;
        GetServiceRequestReferenceNumberResponse other = (GetServiceRequestReferenceNumberResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.getServiceRequestReferenceNumberResult==null && other.getGetServiceRequestReferenceNumberResult()==null) || 
             (this.getServiceRequestReferenceNumberResult!=null &&
              this.getServiceRequestReferenceNumberResult.equals(other.getGetServiceRequestReferenceNumberResult())));
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
        if (getGetServiceRequestReferenceNumberResult() != null) {
            _hashCode += getGetServiceRequestReferenceNumberResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetServiceRequestReferenceNumberResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webqa.net/", ">GetServiceRequestReferenceNumberResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getServiceRequestReferenceNumberResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "GetServiceRequestReferenceNumberResult"));
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
