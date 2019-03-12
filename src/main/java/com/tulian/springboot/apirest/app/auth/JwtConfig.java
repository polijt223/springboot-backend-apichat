package com.tulian.springboot.apirest.app.auth;

public class JwtConfig {
	
	public static final String LLAVA_SECRETA = "alguna.clave.secreta.123456789";
	public static final String RSA_PRIVADA = "-----BEGIN RSA PRIVATE KEY-----\r\n" + 
			"MIIEowIBAAKCAQEAqTwpxkQIrs+lhK748Z0IqDG2k3RhjcgBZcbZM9lkkg+56GE3\r\n" + 
			"h6DWqf4Ie+gN+6c4wy5lyQtHD+PVZyefklF7VuDs/yjZmc9e79go9pSctMhemG8E\r\n" + 
			"o6tuY9kAIvQl/MG2F0svm3GS/D9jAZa1XUU1Q03+YGBXv85sDOzEVWtU5B5f6dP/\r\n" + 
			"Nr+BiBJDqme+4Ku25Eeykw79/4kM48Kka95At8cfK7Yg2KNIp8xyHJCmU4S8f6T3\r\n" + 
			"D4VUwGQ/lwmfcv2HfRYjWXjEQTaQBIxXLdnvC8O4NpAzk66pmivHNszz//YK02i+\r\n" + 
			"NVwhylmPcdxgeqhtp2vLhGu21v5yVrvjUfzGmQIDAQABAoIBAAbuS9eu2ZwvLNwu\r\n" + 
			"PkvG3p/kyVFopsYjtsrwRdz1vkStGDQIJzoKwD/eA4LvkgMIai60K778E0D3V7oN\r\n" + 
			"yV84rxBzzcD1b1P5Ri4UcDkJBRHyUs1zXoFFeBPmwzarQRME6+wwTBbVo+tovSlH\r\n" + 
			"fs3/E5taClzwFSfp0EsHN1M4PhXJh59L1DA8gPGWG39EstFtgrQumlYFlCXu9p2I\r\n" + 
			"lL2Rga1zbEMLpOLe20hbUs8Bnl9R1BqlPAx2cIF8AGuOPd/8zYOtDtg5gBFprIlJ\r\n" + 
			"ztIgcyAfSFMwrIzQ9fqE4Dl9YdoQwef25ahOiXyfwu4wkfqQkLdt9JH4cfIlMmER\r\n" + 
			"2WuApMECgYEA0lA0DP35CfuxsOX+e1B3mkkkOlHUrpTaGh0fT48x68cybfMLPYZ/\r\n" + 
			"0Y/ytqIpI4Yd/UIL8/P/ajLBGP80A/un1O1zXUXEX6uOTRJqyJxNNceOiR6jOBGD\r\n" + 
			"/YC3NdtZR5i45b8V7dEbj+HbQgO57Dd/EIQafyo0xM//7Dpsf8/A6v0CgYEAzf+L\r\n" + 
			"AwCgM9HnhzC+hoIOVCWBdwz3t//xR866AXxQ1sGMhFkbD7gHaKuhun4txRjsVYOr\r\n" + 
			"cxzP1rizX90EZdc48LQEM9UaOxpCl1xaAvA8fCst5HYQ3sCXhTvmwxxZB6Om1CDW\r\n" + 
			"SKV5PtEaAVJUzHnbmi6CIgK/60tePELSZOfnIs0CgYEAur7uyQ6HIR82E97VjgfQ\r\n" + 
			"e9YwbfyVfoO7+m4bjFiZOkQBenHSbEpOmvVXzmXFY77GSEhqNq9U/6aboaeSN2sx\r\n" + 
			"BrfltU+q5EyAKd2Nph5rzLIq1YS356Rd8C5oAFGKQDHTFGYNgcMo1yT0vPR0FdTd\r\n" + 
			"Zfv3Ty1Z99y94bOq/f6qzb0CgYAQuAfJQ5i8+/FFmKAt/Io7LBwY1HWaemxTvqR8\r\n" + 
			"EGHjPCwyZaT7np0DUsK6hxkh4Qb1Xc1hd1oq/CyawC5mMZSOfJ3GsyoA1/3FmRo5\r\n" + 
			"GNRf44VDAI4X9huWFamB9mAHbbHRj6S0mrF/dqscplj5i35WQiYPqpd2+lP4ohe/\r\n" + 
			"Vs78lQKBgFeQctL1Aqt8vDoHKP8Q70CtUXka2+K7uTagQczsWbyKqEOHnRitcMJd\r\n" + 
			"GB4zxF+1omdnbEoSK0ZKBDeZ+q0Vhg2ymoRNzI0j21p+MsyNJ78L60AGEl46HG8T\r\n" + 
			"jYNpNxnDK3VRiof9WLJRFHaOiP/avFSw4ZmqjX3CqYUr1qm6TAWm\r\n" + 
			"-----END RSA PRIVATE KEY-----";
	
	public static final String RSA_PUBLICA = "-----BEGIN PUBLIC KEY-----\r\n" + 
			"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqTwpxkQIrs+lhK748Z0I\r\n" + 
			"qDG2k3RhjcgBZcbZM9lkkg+56GE3h6DWqf4Ie+gN+6c4wy5lyQtHD+PVZyefklF7\r\n" + 
			"VuDs/yjZmc9e79go9pSctMhemG8Eo6tuY9kAIvQl/MG2F0svm3GS/D9jAZa1XUU1\r\n" + 
			"Q03+YGBXv85sDOzEVWtU5B5f6dP/Nr+BiBJDqme+4Ku25Eeykw79/4kM48Kka95A\r\n" + 
			"t8cfK7Yg2KNIp8xyHJCmU4S8f6T3D4VUwGQ/lwmfcv2HfRYjWXjEQTaQBIxXLdnv\r\n" + 
			"C8O4NpAzk66pmivHNszz//YK02i+NVwhylmPcdxgeqhtp2vLhGu21v5yVrvjUfzG\r\n" + 
			"mQIDAQAB\r\n" + 
			"-----END PUBLIC KEY-----";
	
}
