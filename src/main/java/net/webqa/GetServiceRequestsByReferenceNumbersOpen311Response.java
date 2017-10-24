/**
 * GetServiceRequestsByReferenceNumbersOpen311Response.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package net.webqa;

public class GetServiceRequestsByReferenceNumbersOpen311Response  implements java.io.Serializable {
    private net.webqa.GetServiceRequestsByReferenceNumbersOpen311ResponseGetServiceRequestsByReferenceNumbersOpen311Result getServiceRequestsByReferenceNumbersOpen311Result;

    public GetServiceRequestsByReferenceNumbersOpen311Response() {
    }

    public GetServiceRequestsByReferenceNumbersOpen311Response(
           net.webqa.GetServiceRequestsByReferenceNumbersOpen311ResponseGetServiceRequestsByReferenceNumbersOpen311Result getServiceRequestsByReferenceNumbersOpen311Result) {
           this.getServiceRequestsByReferenceNumbersOpen311Result = getServiceRequestsByReferenceNumbersOpen311Result;
    }


    /**
     * Gets the getServiceRequestsByReferenceNumbersOpen311Result value for this GetServiceRequestsByReferenceNumbersOpen311Response.
     * 
     * @return getServiceRequestsByReferenceNumbersOpen311Result
     */
    public net.webqa.GetServiceRequestsByReferenceNumbersOpen311ResponseGetServiceRequestsByReferenceNumbersOpen311Result getGetServiceRequestsByReferenceNumbersOpen311Result() {
        return getServiceRequestsByReferenceNumbersOpen311Result;
    }


    /**
     * Sets the getServiceRequestsByReferenceNumbersOpen311Result value for this GetServiceRequestsByReferenceNumbersOpen311Response.
     * 
     * @param getServiceRequestsByReferenceNumbersOpen311Result
     */
    public void setGetServiceRequestsByReferenceNumbersOpen311Result(net.webqa.GetServiceRequestsByReferenceNumbersOpen311ResponseGetServiceRequestsByReferenceNumbersOpen311Result getServiceRequestsByReferenceNumbersOpen311Result) {
        this.getServiceRequestsByReferenceNumbersOpen311Result = getServiceRequestsByReferenceNumbersOpen311Result;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetServiceRequestsByReferenceNumbersOpen311Response)) return false;
        GetServiceRequestsByReferenceNumbersOpen311Response other = (GetServiceRequestsByReferenceNumbersOpen311Response) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.getServiceRequestsByReferenceNumbersOpen311Result==null && other.getGetServiceRequestsByReferenceNumbersOpen311Result()==null) || 
             (this.getServiceRequestsByReferenceNumbersOpen311Result!=null &&
              this.getServiceRequestsByReferenceNumbersOpen311Result.equals(other.getGetServiceRequestsByReferenceNumbersOpen311Result())));
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
        if (getGetServiceRequestsByReferenceNumbersOpen311Result() != null) {
            _hashCode += getGetServiceRequestsByReferenceNumbersOpen311Result().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetServiceRequestsByReferenceNumbersOpen311Response.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webqa.net/", ">GetServiceRequestsByReferenceNumbersOpen311Response"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getServiceRequestsByReferenceNumbersOpen311Result");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "GetServiceRequestsByReferenceNumbersOpen311Result"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webqa.net/", ">>GetServiceRequestsByReferenceNumbersOpen311Response>GetServiceRequestsByReferenceNumbersOpen311Result"));
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
