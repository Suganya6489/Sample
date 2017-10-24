/**
 * StripHTMLResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package net.webqa;

public class StripHTMLResponse  implements java.io.Serializable {
    private java.lang.String stripHTMLResult;

    public StripHTMLResponse() {
    }

    public StripHTMLResponse(
           java.lang.String stripHTMLResult) {
           this.stripHTMLResult = stripHTMLResult;
    }


    /**
     * Gets the stripHTMLResult value for this StripHTMLResponse.
     * 
     * @return stripHTMLResult
     */
    public java.lang.String getStripHTMLResult() {
        return stripHTMLResult;
    }


    /**
     * Sets the stripHTMLResult value for this StripHTMLResponse.
     * 
     * @param stripHTMLResult
     */
    public void setStripHTMLResult(java.lang.String stripHTMLResult) {
        this.stripHTMLResult = stripHTMLResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof StripHTMLResponse)) return false;
        StripHTMLResponse other = (StripHTMLResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.stripHTMLResult==null && other.getStripHTMLResult()==null) || 
             (this.stripHTMLResult!=null &&
              this.stripHTMLResult.equals(other.getStripHTMLResult())));
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
        if (getStripHTMLResult() != null) {
            _hashCode += getStripHTMLResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(StripHTMLResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webqa.net/", ">StripHTMLResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stripHTMLResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "StripHTMLResult"));
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
