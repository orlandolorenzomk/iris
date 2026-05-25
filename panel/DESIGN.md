# Iris Panel — Design System

AWS-like design language. Flat, utilitarian, data-dense. No gradients, no rounded corners beyond 2–4px, no drop shadows beyond `0 1px 4px`.

---

## Color Tokens

| Token | Hex | Usage |
|---|---|---|
| `--bg-page` | `#F2F3F3` | Page background |
| `--bg-card` | `#FFFFFF` | Cards, panels, table backgrounds |
| `--bg-sidebar` | `#232F3E` | Left sidebar |
| `--bg-sidebar-hover` | `#31465F` | Sidebar item hover |
| `--bg-sidebar-active` | `#31465F` | Sidebar item active state |
| `--bg-table-header` | `#F2F3F3` | Table `<th>` background |
| `--accent` | `#FF9900` | Primary buttons, active sidebar border, highlights |
| `--accent-hover` | `#EC7211` | Primary button hover |
| `--link` | `#0073BB` | Links, focus rings |
| `--text-primary` | `#16191F` | Headings, primary data |
| `--text-secondary` | `#5F6B7A` | Labels, secondary data, descriptions |
| `--text-sidebar` | `#AEBCCC` | Sidebar nav items (inactive) |
| `--text-sidebar-active` | `#FFFFFF` | Sidebar nav items (active) |
| `--border` | `#D5DBDB` | All borders — cards, tables, inputs |
| `--success-bg/fg` | `#EAFAF1` / `#1D8348` | Success states, ACTIVE/READY badges |
| `--error-bg/fg` | `#FDEDEC` / `#C0392B` | Error states, FAILED/UNAVAILABLE badges |
| `--warning-bg/fg` | `#FEF9E7` / `#D68910` | Warning states, PENDING/BOOTSTRAPPING badges |
| `--info-bg/fg` | `#EBF5FB` / `#0073BB` | Info states |

---

## Typography

**Font stack:** `system-ui, -apple-system, "Amazon Ember", sans-serif`
**Mono font:** `"SFMono-Regular", Consolas, "Liberation Mono", Menlo, monospace`

| Use | Size | Weight |
|---|---|---|
| Page title | 20px | 700 |
| Section heading | 16px | 600 |
| Table header | 12px | 600 |
| Body / table data | 13–14px | 400 |
| Monospaced values (IDs, IPs, paths) | 12px | 400 |
| Badge / label | 11px | 600 |

---

## Layout

- **Sidebar width:** `220px` — fixed, never resizable
- **Top bar height:** `48px` — fixed
- **Content padding:** `28px 32px`
- **Card padding:** `20px 24px`
- **Grid gap:** `16px`

---

## Components

### Buttons

```
.btn-primary   → background: #FF9900, color: #000, hover: #EC7211
.btn-secondary → background: #FFF, border: 1px solid #D5DBDB, hover: #F2F3F3
.btn-danger    → transparent, color: #C0392B, border: 1px solid #C0392B, hover bg: #FDEDEC
.btn-icon      → transparent, no border, used for inline ✕ delete actions
```

All buttons: `border-radius: 2px`, `font-size: 13px`, `padding: 7px 16px`.

### Status Badges

Left-colored-border style, no rounded corners beyond 2px.

| Status | Color |
|---|---|
| ACTIVE, READY, RUNNING | Green (`#1D8348` / `#EAFAF1`) |
| BOOTSTRAPPING, PENDING_APPLY, PROVISIONING, PENDING | Amber (`#D68910` / `#FEF9E7`) |
| UNAVAILABLE, FAILED, DESTROYED | Red (`#C0392B` / `#FDEDEC`) |
| STOPPED, DESTROYING, PENDING_DESTROY | Gray (`#5F6B7A` / `#F2F3F3`) |

### Tables

- Header: `#F2F3F3` bg, `2px solid #D5DBDB` bottom border, `12px 600` font
- Row: `11px 16px` padding, `1px solid #D5DBDB` bottom border
- Hover: `#FAFAFA` row background
- Last row: no bottom border

### Cards

```scss
background: #FFFFFF;
border: 1px solid #D5DBDB;
border-radius: 2px;
box-shadow: 0 1px 4px rgba(0,0,0,0.1);
```

### Inputs / Selects

```scss
border: 1px solid #D5DBDB;
border-radius: 2px;
padding: 7px 10px;
focus → border-color: #0073BB + box-shadow: 0 0 0 2px rgba(0,115,187,0.15)
```

---

## Spacing Scale

| Token | Value |
|---|---|
| xs | 4px |
| sm | 8px |
| md | 16px |
| lg | 24px |
| xl | 32px |
| 2xl | 48px |

---

## Do / Don't

**Do:**
- Use `#FF9900` only for the primary CTA and the sidebar active indicator
- Keep border-radius at 2px maximum on all interactive elements
- Use monospace font for all machine IPs, IDs, subnet values, paths
- Left-border-style badges for all status indicators
- Flat table design — no alternating row stripes

**Don't:**
- No gradients anywhere
- No border-radius > 4px
- No purple, pink, or vibrant UI-kit colors
- No box shadows larger than `0 2px 8px rgba(0,0,0,0.15)`
- No Inter, Roboto, or generic Google Fonts — use system stack
- Don't use colored backgrounds for entire rows — only use `#FAFAFA` on hover
- Don't add animations beyond `transition: background 0.15s` on interactive elements
