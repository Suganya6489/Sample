/**
 * GetServiceRequestsByFiltersOpen311Response.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package net.webqa;

public class GetServiceRequestsByFiltersOpen311Response  implements java.io.Serializable {
    private net.webqa.GetServiceRequestsByFiltersOpen311ResponseGetServiceRequestsByFiltersOpen311Result getServiceRequestsByFiltersOpen311Result;

    public GetServiceRequestsByFiltersOpen311Response() {
    }

    public GetServiceRequestsByFiltersOpen311Response(
           net.webqa.GetServiceRequestsByFiltersOpen311ResponseGetServiceRequestsByFiltersOpen311Result getServiceRequestsByFiltersOpen311Result) {
           this.getServiceRequestsByFiltersOpen311Result = getServiceRequestsByFiltersOpen311Result;
    }


    /**
     * Gets the getServiceRequestsByFiltersOpen311Result value for this GetServiceRequestsByFiltersOpen311Response.
     * 
     * @return getServiceRequestsByFiltersOpen311Result
     */
    public net.webqa.GetServiceRequestsByFiltersOpen311ResponseGetServiceRequestsByFiltersOpen311Result getGetServiceRequestsByFiltersOpen311Result() {
        return getServiceRequestsByFiltersOpen311Result;
    }


    /**
     * Sets the getServiceRequestsByFiltersOpen311Result value for this GetServiceRequestsByFiltersOpen311Response.
     * 
     * @param getServiceRequestsByFiltersOpen311Result
     */
    public void setGetServiceRequestsByFiltersOpen311Result(net.webqa.GetServiceRequestsByFiltersOpen311ResponseGetServiceRequestsByFiltersOpen311Result getServiceRequestsByFiltersOpen311Result) {
        this.getServiceRequestsByFiltersOpen311Result = getServiceRequestsByFiltersOpen311Result;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetServiceRequestsByFiltersOpen311Response)) return false;
        GetServiceRequestsByFiltersOpen311Response other = (GetServiceRequestsByFiltersOpen311Response) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.getServiceRequestsByFiltersOpen311Result==null && other.getGetServiceRequestsByFiltersOpen311Result()==null) || 
             (this.getServiceRequestsByFiltersOpen311Result!=null &&
              this.getServiceRequestsByFiltersOpen311Result.equals(other.getGetServiceRequestsByFiltersOpen311Result())));
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
        if (getGetServiceRequestsByFiltersOpen311Result() != null) {
            _hashCode += getGetServiceRequestsByFiltersOpen311Result().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetServiceRequestsByFiltersOpen311Response.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webqa.net/", ">GetServiceRequestsByFiltersOpen311Response"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getServiceRequestsByFiltersOpen311Result");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "GetServiceRequestsByFiltersOpen311Result"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webqa.net/", ">>GetServiceRequestsByFiltersOpen311Response>GetServiceRequestsByFiltersOpen311Result"));
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
