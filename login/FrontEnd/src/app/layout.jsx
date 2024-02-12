// import Navbar from "@/components/Navbar";
import Head from 'next/head';
import { Inter } from 'next/font/google';
import './globals.css';
import ToastProvider from '@/components/ToastProvider';
import { PlayProvider } from '@/components/Threejs/contexts/PlayProvider';
import getCurrentUser from './actions/getCurrentUser';
import AuthSession from './AuthSession';
import { getServerSession } from 'next-auth';
import { authOptions } from '@/utils/authOptions';
import Navbar from '@/components/Navbar/Navbar';
import Footer from '@/components/footer/Footer';

const inter = Inter({ subsets: ['latin'] });

export const metadata = {
  title: 'Create Next App',
  description: 'Generated by create next app',
};

export default async function RootLayout({children})
{

  const session = await getServerSession(authOptions);

  return (
    <html>

        <Head>
            <title>{metadata.title}</title>
            <meta name="description" content={metadata.description} />
          </Head>
          <body>
            <main>
                <AuthSession session={session}>
                  <PlayProvider>
                    <ToastProvider />
                        <Navbar/>
                        {children}
                  </PlayProvider>
                </AuthSession>
            </main>
            <Footer />
          </body>

      
    </html>
  );
}