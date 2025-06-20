# Frontend Structure (Refactored)

This frontend uses Next.js (App Router, TypeScript, Tailwind) and is organized for scalability and maintainability.

## Key Directories

- `src/app/` — Next.js app pages and layout
- `src/components/` — Reusable UI components (e.g., AuthForm)
- `src/features/auth/` — Auth-specific hooks, types, and API logic:
  - `hooks.ts` — Custom hooks for signup/signin logic
  - `api.ts` — API calls for auth
  - `types.ts` — DTOs/interfaces for auth
- `src/hooks/` — Generic custom hooks (future use)
- `src/services/` — API client and service logic (future use)
- `src/utils/` — Utility functions (future use)

## Auth Flow Example
- **Signup/Signin pages** use the `AuthForm` component and custom hooks from `features/auth`.
- All form logic, API calls, and error handling are encapsulated in hooks and feature modules.

## How to Extend
- Add new features in `features/<feature>/`.
- Add new UI components in `components/`.
- Add shared logic in `hooks/`, `services/`, or `utils/` as needed.

---

This structure makes it easy to scale, test, and maintain the codebase.

This is a [Next.js](https://nextjs.org) project bootstrapped with [`create-next-app`](https://nextjs.org/docs/app/api-reference/cli/create-next-app).

## Getting Started

First, run the development server:

```bash
npm run dev
# or
yarn dev
# or
pnpm dev
# or
bun dev
```

Open [http://localhost:3000](http://localhost:3000) with your browser to see the result.

You can start editing the page by modifying `app/page.tsx`. The page auto-updates as you edit the file.

This project uses [`next/font`](https://nextjs.org/docs/app/building-your-application/optimizing/fonts) to automatically optimize and load [Geist](https://vercel.com/font), a new font family for Vercel.

## Learn More

To learn more about Next.js, take a look at the following resources:

- [Next.js Documentation](https://nextjs.org/docs) - learn about Next.js features and API.
- [Learn Next.js](https://nextjs.org/learn) - an interactive Next.js tutorial.

You can check out [the Next.js GitHub repository](https://github.com/vercel/next.js) - your feedback and contributions are welcome!

## Deploy on Vercel

The easiest way to deploy your Next.js app is to use the [Vercel Platform](https://vercel.com/new?utm_medium=default-template&filter=next.js&utm_source=create-next-app&utm_campaign=create-next-app-readme) from the creators of Next.js.

Check out our [Next.js deployment documentation](https://nextjs.org/docs/app/building-your-application/deploying) for more details.
