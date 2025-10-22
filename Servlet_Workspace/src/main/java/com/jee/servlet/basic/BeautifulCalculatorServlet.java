package com.jee.servlet.basic;



import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class BeautifulCalculatorServlet implements Servlet {
    private ServletConfig config;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.config = config;
    }

    @Override
    public ServletConfig getServletConfig() {
        return this.config;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        // We'll cast to HttpServletRequest to read HTTP method and parameters easily.
        HttpServletRequest request = (req instanceof HttpServletRequest) ? (HttpServletRequest) req : null;
        res.setContentType("text/html; charset=UTF-8");
        PrintWriter out = res.getWriter();

        // Read parameters safely
        String op = req.getParameter("op");
        String aStr = req.getParameter("a");
        String bStr = req.getParameter("b");
        String message = null;
        String resultStr = "";

        if (op != null && aStr != null && bStr != null) {
            try {
                // Use BigDecimal for accurate arithmetic and nice formatting
                BigDecimal a = new BigDecimal(aStr.trim());
                BigDecimal b = new BigDecimal(bStr.trim());
                BigDecimal result;

                switch (op) {
                    case "add":
                        result = a.add(b);
                        resultStr = format(result);
                        break;
                    case "sub":
                        result = a.subtract(b);
                        resultStr = format(result);
                        break;
                    case "mul":
                        result = a.multiply(b);
                        resultStr = format(result);
                        break;
                    case "div":
                        if (b.compareTo(BigDecimal.ZERO) == 0) {
                            message = "Division by zero is not allowed.";
                        } else {
                            // scale to 8 decimal places max for display
                            result = a.divide(b, 8, BigDecimal.ROUND_HALF_UP).stripTrailingZeros();
                            resultStr = format(result);
                        }
                        break;
                    default:
                        message = "Unknown operation.";
                }
            } catch (NumberFormatException nfe) {
                message = "Please enter valid numbers.";
            } catch (ArithmeticException ae) {
                message = "Arithmetic error: " + ae.getMessage();
            }
        }

        // Render HTML + CSS + small JS for interactivity
        out.println("<!doctype html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("<meta charset='utf-8'/>");
        out.println("<meta name='viewport' content='width=device-width,initial-scale=1'/>");
        out.println("<title>Beautiful Servlet Calculator</title>");
        out.println("<style>");
        out.println("  :root{--bg:#0f1724;--card:#0b1220;--accent:#60a5fa;--muted:#94a3b8;--glass:rgba(255,255,255,0.03)}");
        out.println("  body{ margin:0; font-family:Inter,Segoe UI,Roboto,Arial; background: linear-gradient(135deg,#071026 0%, #0b1930 100%); color:#e6eef8; display:flex; align-items:center; justify-content:center; min-height:100vh; }");
        out.println("  .card{ width:min(720px,96%); background:linear-gradient(180deg, rgba(255,255,255,0.02), rgba(255,255,255,0.01)); border-radius:16px; box-shadow: 0 8px 30px rgba(2,6,23,0.7); padding:28px; display:grid; grid-template-columns: 1fr 320px; gap:20px; }");
        out.println("  h1{ margin:0 0 8px 0; font-size:20px; letter-spacing:0.4px }");
        out.println("  p.lead{ margin:0 0 18px 0; color:var(--muted)}");
        out.println("  form{ display:flex; flex-direction:column; gap:12px }");
        out.println("  .inputs{ display:flex; gap:10px }");
        out.println("  input[type='text']{ flex:1; padding:12px 14px; border-radius:10px; border:1px solid var(--glass); background:transparent; color:inherit; outline:none; box-shadow:inset 0 1px 0 rgba(255,255,255,0.02)}");
        out.println("  .ops{ display:flex; gap:8px; margin-top:6px }");
        out.println("  button.op{ flex:1; padding:10px 12px; border-radius:10px; border: none; cursor:pointer; background:rgba(255,255,255,0.03); color:inherit; transition: transform .12s, background .12s; }");
        out.println("  button.op:hover{ transform:translateY(-3px); background: linear-gradient(90deg, rgba(96,165,250,0.14), rgba(96,165,250,0.08)); box-shadow:0 6px 18px rgba(96,165,250,0.06)}");
        out.println("  .display{ background:rgba(255,255,255,0.02); border-radius:12px; padding:18px; display:flex; flex-direction:column; gap:8px; align-items:flex-start }");
        out.println("  .result{ font-size:28px; font-weight:600; color:var(--accent) }");
        out.println("  .meta{ color:var(--muted); font-size:13px }");
        out.println("  .footer{ margin-top:10px; color:var(--muted); font-size:13px }");
        out.println("  @media(max-width:820px){ .card{ grid-template-columns: 1fr; } }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='card' role='main'>");

        // Left: Input form
        out.println("<div>");
        out.println("<h1>Beautiful Calculator</h1>");
        out.println("<p class='lead'>Perform precise arithmetic. Try big numbers, decimals and see the result instantly.</p>");
        out.println("<form method='post' id='calcForm'>");

        out.println("<div class='inputs'>");
        out.println("<input name='a' id='a' type='text' placeholder='Enter first number (e.g. 12.5)' value='" + safe(aStr) + "'/>");
        out.println("<input name='b' id='b' type='text' placeholder='Enter second number (e.g. 3.14)' value='" + safe(bStr) + "'/>");
        out.println("</div>");

        out.println("<div class='ops'>");
        out.println("<button class='op' type='submit' name='op' value='add'> + Add</button>");
        out.println("<button class='op' type='submit' name='op' value='sub'> − Subtract</button>");
        out.println("<button class='op' type='submit' name='op' value='mul'> × Multiply</button>");
        out.println("<button class='op' type='submit' name='op' value='div'> ÷ Divide</button>");
        out.println("</div>");

        out.println("</form>");
        out.println("<div class='footer'>Tip: Use decimals and large numbers. Division displays up to 8 decimal places.</div>");
        out.println("</div>"); // left

        // Right: Display area
        out.println("<div class='display' aria-live='polite'>");
        out.println("<div class='meta'>Last operation:</div>");
        if (op == null) {
            out.println("<div class='result'>— no operation yet —</div>");
        } else {
            String prettyOp = prettyOp(op);
            out.println("<div class='result'>" + safe(aStr) + " " + prettyOp + " " + safe(bStr) + " = " + (message == null ? safe(resultStr) : "<span style='color:#ff7b7b'>" + safe(message) + "</span>") + "</div>");
        }
        out.println("<div class='meta'>Powered by a servlet implementing <code>Servlet</code>. Responsive layout with subtle micro-interactions.</div>");
        out.println("</div>"); // right

        out.println("</div>"); // card
        out.println("<script>");
        out.println("  // small client-side validation to enhance UX");
        out.println("  document.getElementById('calcForm').addEventListener('submit', function(e){");
        out.println("    const a = document.getElementById('a').value.trim();");
        out.println("    const b = document.getElementById('b').value.trim();");
        out.println("    if(a === '' || b === ''){ e.preventDefault(); alert('Please fill both numbers.'); }");
        out.println("    // allow negative and decimal numbers, basic sanity check");
        out.println("    const valid = /^[-+]?[0-9]*\\.?[0-9]+$/.test(a) && /^[-+]?[0-9]*\\.?[0-9]+$/.test(b);");
        out.println("    if(!valid){ e.preventDefault(); alert('Please enter valid numbers (digits, optional decimal point).'); }");
        out.println("  });");
        out.println("</script>");
        out.println("</body>");
        out.println("</html>");
        out.flush();
    }

    @Override
    public String getServletInfo() {
        return "BeautifulCalculatorServlet v1.0 — a polished arithmetic servlet";
    }

    @Override
    public void destroy() {
        // cleanup if needed
    }

    // --- helper methods ---
    private static String format(BigDecimal n) {
        // Nice short format: avoid scientific notation for usual ranges
        DecimalFormat df = new DecimalFormat("0.########");
        return df.format(n);
    }

    private static String prettyOp(String op) {
        switch (op) {
            case "add": return "+";
            case "sub": return "−";
            case "mul": return "×";
            case "div": return "÷";
            default: return "?";
        }
    }

    private static String safe(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
    }
}

