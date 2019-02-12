package com.danning.silkroad.helipay.common.constents;

import org.springframework.beans.factory.annotation.Value;

/**
 * 类名称：AuthConstants<br>
 * 类描述：<br>
 * 创建时间：2019年01月25日<br>
 *
 * @author 陈超
 * @version 1.0.0
 */
public class AuthConstants {

    /**
     * 商户号 合利宝分配
     */
    public static final String CUSTOMERNUMBER="C1800532519";

    /**
     * 公钥
     */
    public static final String privatekey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALlnW9pzzxxrZRfVlh/z0wKjnnVXO6yHyCQ54mBts55p8yL2G0xHrjwO04pIlgKyo9qnsdyCgsmB8Vv1uropBzLpd1H/n4hUTbAwZDr5wHTFD/sHVZQfW5xI1B2wi4TH+C7UN8/dLwngQxj5VPWftArxP6cTAqcLsA2E+Rj0B/1vAgMBAAECgYA1+uLian9tjdMkKlqIOQxBo3O6REiqhen+qh+/5ZC7WauhjTgLcH9JGyP6CgIrDOnp+pMtWY5MrxmfpZ7VHqbqa/NdYRoQjjDvGwwQSJ/vbjU84qwFnTJuuZkqKhxPtmhaXd2HeuQ1XFJ3JIj1hFKFOE8BE/s0esIbXnOXTFbxyQJBANugQwJBxwvwTCA7FwPT7q5bF6plOd3WE/xcl/FH6gKRTIDW1Fg2IDP9K4vNZT9jBJqRu2deN3iFce79IaPzbOMCQQDYHCK4F0hljXHbBAapyExsNt2mGah4m+zy4vAi3JhcIp/AClWNRJQRkemGDbOJfb+Ad/6Z8TLkgLMcZCKRiT8FAkBGo7JJzIxP67cgesD5SzYuwD/JX4uQLWX6OnHoeh/rOX6oxfBAexA6iIh0C9kv3RJDAPKM2cHvssQlgSdLy2TPAkEAnCaoR5A7NXWggPpFvkQGRGLYgP4lJxCzg0TK8sWmKVyzRVWyP2MAAjoGHGCmC4Ca8L7DboRDm6wKv0bzW2lp6QJAYm2lz45VzwBVvnXECL4zL8I9SYNutOVwsGMRfSA2EoNyaYzO6S6UC3U4auW8vsXpmtamuhw8lQapgmgpSlLjjA==";

    /**
     * 私钥
     */
    public static final String publickey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDonLTXehZeFwmsg+zs8AHrsgGPkGXLS7Lhf2RMOHmAHC/MsvnrfHfu0GD0FcX7NuXKBJK7KsA0MWlEkHezuoRkZS2xZ79qQzImSVE3POO4g1ZVTsW2Bl9LNN2mkTAsum6ik/vodYzftbS0tT73SvgHk54mAm8cWdiQOEVmdX0yhQIDAQAB";

    /**
     * 代付-测试环境
     */
    public static final String urlTransfer = "http://test.trx.helipay.com/trx/transfer/interface.action";

    /**
     * 代付-生产环境
     */
    //public static final String urlTransfer = "http://transfer.trx.helipay.com/trx/transfer/interface.action";
    /**
     * 快捷支付测试环境
     */
    public static final String REQUEST_URL_QUICKPAY = "http://test.trx.helipay.com/trx/quickPayApi/interface.action";

    /**
     * 快捷支付生产环境
     */
    //public static final String REQUEST_URL_QUICKPAY = "http://pay.trx.helipay.com/trx/quickPayApi/interface.action";
    /**
     * 商户代付的签名密钥
     */
    public static final String signKey = "ZzkCVvV3aLKSlVifoLQuiBe0bP4HeROj";

    /**
     * 合利宝cert地址
     */
    public static final String CERT_PATH = "d:/helipay.cer";

    /**
     * 商户pfx地址
     */
    public static final String PFX_PATH = "d:/zl-cert.pfx";

    /**
     * pfx密码
     */
    public static final String PFX_PWD = "Dnwl2019";
}
