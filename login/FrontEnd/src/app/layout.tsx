// import Navbar from "@/components/Navbar";
import Head from 'next/head';
import { Inter } from 'next/font/google';
import './globals.css';
import ToastProvider from '@/components/ToastProvider';
import { PlayProvider } from '@/components/Threejs/contexts/PlayProvider';
import getCurrentUser from './actions/getCurrentUser';

const inter = Inter({ subsets: ['latin'] });

export const metadata = {
  title: 'Create Next App',
  description: 'Generated by create next app',
};

export default async function RootLayout({children}:Readonly<{children: React.ReactNode;}>)
{

  return (
    <html>

        <Head>
            <title>{metadata.title}</title>
            <meta name="description" content={metadata.description} />
          </Head>
          <body>
            <main>
            <PlayProvider>
                <ToastProvider />
              {children}
            </PlayProvider>
            </main>
          </body>

      
    </html>
  );
}