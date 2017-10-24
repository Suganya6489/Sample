/**
 * GetServiceRequestsByFilters.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package net.webqa;

public class GetServiceRequestsByFilters  implements java.io.Serializable {
    private java.lang.String authKey;

    private java.lang.String serviceRequestTypeIDs;

    private java.lang.String statusIDs;

    private java.lang.String startDate;

    private java.lang.String endDate;

    public GetServiceRequestsByFilters() {
    }

    public GetServiceRequestsByFilters(
           java.lang.String authKey,
           java.lang.String serviceRequestTypeIDs,
           java.lang.String statusIDs,
           java.lang.String startDate,
           java.lang.String endDate) {
           this.authKey = authKey;
           this.serviceRequestTypeIDs = serviceRequestTypeIDs;
           this.statusIDs = statusIDs;
           this.startDate = startDate;
           this.endDate = endDate;
    }


    /**
     * Gets the authKey value for this GetServiceRequestsByFilters.
     * 
     * @return authKey
     */
    public java.lang.String getAuthKey() {
        return authKey;
    }


    /**
     * Sets the authKey value for this GetServiceRequestsByFilters.
     * 
     * @param authKey
     */
    public void setAuthKey(java.lang.String authKey) {
        this.authKey = authKey;
    }


    /**
     * Gets the serviceRequestTypeIDs value for this GetServiceRequestsByFilters.
     * 
     * @return serviceRequestTypeIDs
     */
    public java.lang.String getServiceRequestTypeIDs() {
        return serviceRequestTypeIDs;
    }


    /**
     * Sets the serviceRequestTypeIDs value for this GetServiceRequestsByFilters.
     * 
     * @param serviceRequestTypeIDs
     */
    public void setServiceRequestTypeIDs(java.lang.String serviceRequestTypeIDs) {
        this.serviceRequestTypeIDs = serviceRequestTypeIDs;
    }


    /**
     * Gets the statusIDs value for this GetServiceRequestsByFilters.
     * 
     * @return statusIDs
     */
    public java.lang.String getStatusIDs() {
        return statusIDs;
    }


    /**
     * Sets the statusIDs value for this GetServiceRequestsByFilters.
     * 
     * @param statusIDs
     */
    public void setStatusIDs(java.lang.String statusIDs) {
        this.statusIDs = statusIDs;
    }


    /**
     * Gets the startDate value for this GetServiceRequestsByFilters.
     * 
     * @return startDate
     */
    public java.lang.String getStartDate() {
        return startDate;
    }


    /**
     * Sets the startDate value for this GetServiceRequestsByFilters.
     * 
     * @param startDate
     */
    public void setStartDate(java.lang.String startDate) {
        this.startDate = startDate;
    }


    /**
     * Gets the endDate value for this GetServiceRequestsByFilters.
     * 
     * @return endDate
     */
    public java.lang.String getEndDate() {
        return endDate;
    }


    /**
     * Sets the endDate value for this GetServiceRequestsByFilters.
     * 
     * @param endDate
     */
    public void setEndDate(java.lang.String endDate) {
        this.endDate = endDate;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetServiceRequestsByFilters)) return false;
        GetServiceRequestsByFilters other = (GetServiceRequestsByFilters) obj;
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
            ((this.serviceRequestTypeIDs==null && other.getServiceRequestTypeIDs()==null) || 
             (this.serviceRequestTypeIDs!=null &&
              this.serviceRequestTypeIDs.equals(other.getServiceRequestTypeIDs()))) &&
            ((this.statusIDs==null && other.getStatusIDs()==null) || 
             (this.statusIDs!=null &&
              this.statusIDs.equals(other.getStatusIDs()))) &&
            ((this.startDate==null && other.getStartDate()==null) || 
             (this.startDate!=null &&
              this.startDate.equals(other.getStartDate()))) &&
            ((this.endDate==null && other.getEndDate()==null) || 
             (this.endDate!=null &&
              this.endDate.equals(other.getEndDate())));
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
        if (getServiceRequestTypeIDs() != null) {
            _hashCode += getServiceRequestTypeIDs().hashCode();
        }
        if (getStatusIDs() != null) {
            _hashCode += getStatusIDs().hashCode();
        }
        if (getStartDate() != null) {
            _hashCode += getStartDate().hashCode();
        }
        if (getEndDate() != null) {
            _hashCode += getEndDate().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetServiceRequestsByFilters.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webqa.net/", ">GetServiceRequestsByFilters"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("authKey");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "authKey"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceRequestTypeIDs");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "serviceRequestTypeIDs"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("statusIDs");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "statusIDs"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("startDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "startDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("endDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "endDate"));
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
