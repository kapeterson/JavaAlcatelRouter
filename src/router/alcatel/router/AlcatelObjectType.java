package router.alcatel.router;

/**
 * Enum for Alcatel Object typeobject types.  Each new object should be added here and can later be used to
 * determin the type of object you are working with.
 * @author Kris Peterson
 *
 */
public enum AlcatelObjectType { 
	
	NONE,
	HARDWAREOBJECT,
	CHASSIS,
	
	BANDWIDTHPOLICY,
	
	IOM, 
	MDA, 
	FPINGRESS,
	MDANETWORK,
	MDAACCESS,
	MDANETWORKINGRESS,
	MCASTPATHMANAGEMENT,
	
	CPM,
	IOMFP,
	XF, 
	PHYSICALPORT,
	PORTETHERNET,
	ETHERNETNETWORK,
	LAG,
	QOSPOLICY,
	
	NETWORKQOSPOLICY,
	NETWORKQUEUEQOSPOLICY,
	SAPQOSPOLICY,
	SAPINGRESSQOSPOLICY,
	SAPEGRESSQOSPOLICY,
	
	SRIPFILTER,
	SRIPFILTERENTRY,
	
	//NETWORKQOSPOLICYQUEUE,  // Network policy doesn't have queue
	SAPQOSPOLICYQUEUE,
	NETWORKQUEUEQOSPOLICYQUEUE,
	ROUTERINTERFACE,
	ROUTERNETWORKINTERFACE,
	STATICROUTE,
	
	OSPFAREA,
	OSPFINTERFACE,
	
	PIMINTERFACE,
	IGMPINTERFACE,
	IGMPSSM,
	IGMPSTATICJOIN,
	MPLSINTERFACE,
	MPLSPATH,
	MPLSPATHHOP,
	MPLSLSP,
	
	RSVPINTERFACE,
	LDPINTERFACE,
	
	SERVICE,
	SDPOBJECT,
	VPLSSERVICE,
	IESSERVICE,
	VPRNSERVICE,
	EPIPESERVICE,
	SERVICEINTERFACE,
	
	SAPOBJECT,
	SAPINGRESSOBJECT,
	SAPEGRESSOBJECT,
	SERVICESDPOBJECT,
	SPOKESDPOBJECT,
	MESHSDPOBJECT,
	SDPINGRESS,
	SDPEGRESS,
	
	POLICYCOMMUNITY,
	POLICYSTATEMENT,
	
	BGPGROUP,
	};

