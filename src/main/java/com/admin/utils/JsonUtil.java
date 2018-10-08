package com.admin.utils;

/**
 *
 * ClassName: JsonUtil <br/>
 * Function: json工具类. <br/>
 * Date: 2016年3月11日 上午11:25:17 <br/>
 *
 * @author qiaocf
 * @version 1.0
 * @since JDK 1.7
 */
public abstract class JsonUtil {
    /**
     *
     * formatJson,(格式化json串). <br/>
     * Author: qiaocf <br/>
     * Create Date: 2016年3月11日 <br/>
     * ===============================================================<br/>
     * Modifier: qiaocf <br/>
     * Modify Date: 2016年3月11日 <br/>
     * Modify Description: <br/>
     * ===============================================================<br/>
     *
     * @param jsonStr jsonStr
     * @return String
     * @since JDK 1.7
     */
    public static String formatJson(String jsonStr) {
        if (jsonStr == null || "".equals(jsonStr)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        char last = '\000';
        char current = '\000';
        int indent = 0;
        for (int i = 0; i < jsonStr.length(); i++) {
            last = current;
            current = jsonStr.charAt(i);
            switch (current) {
            case '[':
            case '{':
                sb.append(current);
                sb.append('\n');
                indent++;
                addIndentBlank(sb, indent);
                break;
            case ']':
            case '}':
                sb.append('\n');
                indent--;
                addIndentBlank(sb, indent);
                sb.append(current);
                break;
            case ',':
                sb.append(current);
                if (last != '\\') {
                    sb.append('\n');
                    addIndentBlank(sb, indent);
                }
                break;
            case 'ξ':
                sb.append('\n');
                addIndentBlank(sb, indent + 1);
                break;
            default:
                sb.append(current);
            }
        }

        return sb.toString();
    }

    /**
     *
     * addIndentBlank,(addIndentBlank). <br/>
     * Author: qiaocf <br/>
     * Create Date: 2016年3月11日 <br/>
     * ===============================================================<br/>
     * Modifier: qiaocf <br/>
     * Modify Date: 2016年3月11日 <br/>
     * Modify Description: <br/>
     * ===============================================================<br/>
     *
     * @param sb StringBuilder类
     * @param indent String
     * @since JDK 1.7
     */
    private static void addIndentBlank(StringBuilder sb, int indent) {
        for (int i = 0; i < indent; i++) {
            sb.append('\t');
        }
    }
}