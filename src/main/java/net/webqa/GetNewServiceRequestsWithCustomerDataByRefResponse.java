/**
 * GetNewServiceRequestsWithCustomerDataByRefResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package net.webqa;

public class GetNewServiceRequestsWithCustomerDataByRefResponse  implements java.io.Serializable {
    private net.webqa.GetNewServiceRequestsWithCustomerDataByRefResponseGetNewServiceRequestsWithCustomerDataByRefResult getNewServiceRequestsWithCustomerDataByRefResult;

    public GetNewServiceRequestsWithCustomerDataByRefResponse() {
    }

    public GetNewServiceRequestsWithCustomerDataByRefResponse(
           net.webqa.GetNewServiceRequestsWithCustomerDataByRefResponseGetNewServiceRequestsWithCustomerDataByRefResult getNewServiceRequestsWithCustomerDataByRefResult) {
           this.getNewServiceRequestsWithCustomerDataByRefResult = getNewServiceRequestsWithCustomerDataByRefResult;
    }


    /**
     * Gets the getNewServiceRequestsWithCustomerDataByRefResult value for this GetNewServiceRequestsWithCustomerDataByRefResponse.
     * 
     * @return getNewServiceRequestsWithCustomerDataByRefResult
     */
    public net.webqa.GetNewServiceRequestsWithCustomerDataByRefResponseGetNewServiceRequestsWithCustomerDataByRefResult getGetNewServiceRequestsWithCustomerDataByRefResult() {
        return getNewServiceRequestsWithCustomerDataByRefResult;
    }


    /**
     * Sets the getNewServiceRequestsWithCustomerDataByRefResult value for this GetNewServiceRequestsWithCustomerDataByRefResponse.
     * 
     * @param getNewServiceRequestsWithCustomerDataByRefResult
     */
    public void setGetNewServiceRequestsWithCustomerDataByRefResult(net.webqa.GetNewServiceRequestsWithCustomerDataByRefResponseGetNewServiceRequestsWithCustomerDataByRefResult getNewServiceRequestsWithCustomerDataByRefResult) {
        this.getNewServiceRequestsWithCustomerDataByRefResult = getNewServiceRequestsWithCustomerDataByRefResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetNewServiceRequestsWithCustomerDataByRefResponse)) return false;
        GetNewServiceRequestsWithCustomerDataByRefResponse other = (GetNewServiceRequestsWithCustomerDataByRefResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.getNewServiceRequestsWithCustomerDataByRefResult==null && other.getGetNewServiceRequestsWithCustomerDataByRefResult()==null) || 
             (this.getNewServiceRequestsWithCustomerDataByRefResult!=null &&
              this.getNewServiceRequestsWithCustomerDataByRefResult.equals(other.getGetNewServiceRequestsWithCustomerDataByRefResult())));
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
        if (getGetNewServiceRequestsWithCustomerDataByRefResult() != null) {
            _hashCode += getGetNewServiceRequestsWithCustomerDataByRefResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetNewServiceRequestsWithCustomerDataByRefResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webqa.net/", ">GetNewServiceRequestsWithCustomerDataByRefResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getNewServiceRequestsWithCustomerDataByRefResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "GetNewServiceRequestsWithCustomerDataByRefResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webqa.net/", ">>GetNewServiceRequestsWithCustomerDataByRefResponse>GetNewServiceRequestsWithCustomerDataByRefResult"));
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
