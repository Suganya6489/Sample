/**
 * GetNewServiceRequestsWithCustomerDataResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package net.webqa;

public class GetNewServiceRequestsWithCustomerDataResponse  implements java.io.Serializable {
    private net.webqa.GetNewServiceRequestsWithCustomerDataResponseGetNewServiceRequestsWithCustomerDataResult getNewServiceRequestsWithCustomerDataResult;

    public GetNewServiceRequestsWithCustomerDataResponse() {
    }

    public GetNewServiceRequestsWithCustomerDataResponse(
           net.webqa.GetNewServiceRequestsWithCustomerDataResponseGetNewServiceRequestsWithCustomerDataResult getNewServiceRequestsWithCustomerDataResult) {
           this.getNewServiceRequestsWithCustomerDataResult = getNewServiceRequestsWithCustomerDataResult;
    }


    /**
     * Gets the getNewServiceRequestsWithCustomerDataResult value for this GetNewServiceRequestsWithCustomerDataResponse.
     * 
     * @return getNewServiceRequestsWithCustomerDataResult
     */
    public net.webqa.GetNewServiceRequestsWithCustomerDataResponseGetNewServiceRequestsWithCustomerDataResult getGetNewServiceRequestsWithCustomerDataResult() {
        return getNewServiceRequestsWithCustomerDataResult;
    }


    /**
     * Sets the getNewServiceRequestsWithCustomerDataResult value for this GetNewServiceRequestsWithCustomerDataResponse.
     * 
     * @param getNewServiceRequestsWithCustomerDataResult
     */
    public void setGetNewServiceRequestsWithCustomerDataResult(net.webqa.GetNewServiceRequestsWithCustomerDataResponseGetNewServiceRequestsWithCustomerDataResult getNewServiceRequestsWithCustomerDataResult) {
        this.getNewServiceRequestsWithCustomerDataResult = getNewServiceRequestsWithCustomerDataResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetNewServiceRequestsWithCustomerDataResponse)) return false;
        GetNewServiceRequestsWithCustomerDataResponse other = (GetNewServiceRequestsWithCustomerDataResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.getNewServiceRequestsWithCustomerDataResult==null && other.getGetNewServiceRequestsWithCustomerDataResult()==null) || 
             (this.getNewServiceRequestsWithCustomerDataResult!=null &&
              this.getNewServiceRequestsWithCustomerDataResult.equals(other.getGetNewServiceRequestsWithCustomerDataResult())));
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
        if (getGetNewServiceRequestsWithCustomerDataResult() != null) {
            _hashCode += getGetNewServiceRequestsWithCustomerDataResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetNewServiceRequestsWithCustomerDataResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webqa.net/", ">GetNewServiceRequestsWithCustomerDataResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getNewServiceRequestsWithCustomerDataResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "GetNewServiceRequestsWithCustomerDataResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webqa.net/", ">>GetNewServiceRequestsWithCustomerDataResponse>GetNewServiceRequestsWithCustomerDataResult"));
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
