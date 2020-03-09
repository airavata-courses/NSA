"""
A script for comparing the e Vulpiani, Maesaka and Schneedbeli methods
for KDP estimation with sample data.
"""

import numpy as np
import matplotlib.pyplot as plt

from pyart.retrieve.kdp_proc import kdp_maesaka, kdp_vulpiani, kdp_schneebeli
from pyart.testing import sample_objects
from pyart.config import get_field_name


def _make_real_psidp_radar():
    """
    Create single-ray radar with linear differential phase profile with
    specified slope.
    ---
    Returns
    -------
    radar : Radar
            PyART radar instance with differential phase profile in deg.
    """
    psidp = np.array([[-4.63150024e-01,
                     -2.33313751e+00, 1.80617523e+00, 7.17742920e-01,
                     1.84783936e-01, 0.00000000e+00,  -2.70583344e+00,
                     3.65928650e-01,  -2.36213684e-01,  -3.37257385e-01,
                     1.61666870e-01, 2.30583191e-01, 1.09773254e+00,
                     -4.30160522e-01,  -1.47248840e+00,  -4.44534302e-01,
                     -2.71575928e-01,  -1.05207062e+00,  -1.78634644e-01,
                     -1.82469940e+00,  -1.21163177e+00,  -1.45535278e+00,
                     4.38339233e-01, 9.89532471e-03, 1.21952057e+00,
                     -3.13832092e+00, 3.32151794e+00, 2.13058472e-01,
                     -3.28111267e+00,  -9.30290222e-01, 2.05941010e+00,
                     -4.32477570e+00, 2.62687683e-01, 6.97067261e-01,
                     -2.48565674e-02, 3.63685608e+00, 1.78419495e+00,
                     -3.00376892e-01,  -1.81982422e+00, 1.29885101e+00,
                     -4.99184418e+00,  -2.15151978e+00, 5.96153259e-01,
                     -2.99251556e+00,  -1.82048035e+00, 5.45096588e+00,
                     1.88364410e+00, 2.88166809e+00, 3.60325623e+00,
                     3.64759064e+00, 4.05049896e+00, 2.48751068e+00,
                     9.06303406e+00,  -2.10025024e+00, 1.00816193e+01,
                     8.22235870e+00, 1.19126892e+01, 4.86039734e+00,
                     7.14872742e+00,  -1.95607758e+00, 3.51721954e+00,
                     -4.81446075e+00,  -1.00240326e+01, 5.14200592e+00,
                     1.10801697e-01,  -2.91020203e+00, 4.49132538e+00,
                     -4.65164185e-01, 2.01962280e+00,  -3.34449768e+00,
                     3.05713654e+00,  -4.38473511e+00, 7.73635101e+00,
                     -3.00103760e+00, 1.05568695e+00, 1.11760635e+01,
                     1.01579590e+01,  -1.64299774e+00, 1.94557953e+00,
                     -6.24208832e+00,  -9.25166321e+00, 4.27034760e+00,
                     -9.00566101e-01, 7.35614777e+00,  -3.68530273e-01,
                     1.78909302e-01, 6.77828217e+00, 2.04873657e+00,
                     6.44758606e+00, 1.55094910e+00, 1.03894043e+00,
                     1.11590118e+01,  -2.74114227e+00, 1.96223450e+00,
                     5.77777100e+00, 6.69315338e+00, 3.33438873e+00,
                     -7.87300110e-01, 2.87608337e+00,  -2.63780975e+00,
                     1.25542984e+01, 7.30712128e+00, 5.04513550e+00,
                     -1.14353180e+00, 4.77389526e+00,  -5.79799652e+00,
                     1.52477951e+01, 3.69001770e+00, 6.05420685e+00,
                     3.05950928e+00, 7.58821869e+00, 8.82480621e+00,
                     3.33154297e+00, 4.47459412e+00,  -1.89208221e+00,
                     6.25183105e+00,  -5.79544067e-01, 2.11674500e+00,
                     1.01202621e+01, 9.55703735e-01, 3.83499908e+00,
                     6.01098633e+00, 6.24042511e+00, 5.05715179e+00,
                     2.48760223e+00, 1.43062592e+00, 7.70075226e+00,
                     3.58940125e+00, 4.11083221e+00, 7.64762878e-01,
                     3.70725250e+00, 6.92240143e+00, 6.44252777e+00,
                     4.86474609e+00, 4.14801788e+00, 8.17996216e+00,
                     4.74959564e+00, 7.00319672e+00, 8.05104065e+00,
                     7.03157043e+00, 6.07535553e+00, 7.52233124e+00,
                     7.08416748e+00, 8.63216400e+00, 1.00612411e+01,
                     9.45279694e+00, 6.99411774e+00, 9.08544159e+00,
                     8.94741058e+00, 5.59152222e+00, 3.96552277e+00,
                     1.16263733e+01, 5.56375885e+00, 1.09810715e+01,
                     1.06725998e+01, 1.21604843e+01, 1.13759689e+01,
                     8.19690704e+00, 1.14421616e+01, 1.00967026e+01,
                     8.75296783e+00, 7.25759888e+00, 1.22137299e+01,
                     7.74095154e+00, 9.35678864e+00, 1.04882660e+01,
                     9.43731689e+00, 1.39332428e+01, 1.42021942e+01,
                     1.08509979e+01, 4.88009644e+00, 7.14031219e+00,
                     4.84495544e+00, 1.14619980e+01, 1.23953857e+01,
                     7.70539093e+00, 1.14259720e+01, 1.13200226e+01,
                     6.78279877e+00, 1.19683990e+01, 1.05512466e+01,
                     1.37246628e+01, 1.35013733e+01, 1.16156921e+01,
                     1.09029236e+01, 1.19279709e+01, 1.20467606e+01,
                     1.01116638e+01, 7.42034149e+00, 8.82723236e+00,
                     1.33992996e+01, 1.31808701e+01, 1.18817520e+01,
                     1.32927246e+01, 9.89555359e+00, 9.84140778e+00,
                     1.12870865e+01, 8.88268280e+00, 1.03469849e+01,
                     1.22974243e+01, 1.05293198e+01, 1.35461273e+01,
                     1.28039017e+01, 1.30093231e+01, 1.10043640e+01,
                     1.41940308e+01, 1.41589813e+01, 1.04827347e+01,
                     1.33864059e+01, 1.23348083e+01, 1.24742508e+01,
                     1.20997391e+01, 9.83790588e+00, 1.25035629e+01,
                     1.33076096e+01, 1.30602951e+01, 1.27498856e+01,
                     1.33953705e+01, 1.24620361e+01, 1.35252457e+01,
                     1.28876648e+01, 1.24350815e+01, 1.15471649e+01,
                     1.17637405e+01, 1.31310349e+01, 1.21519089e+01,
                     1.29200668e+01, 1.42670364e+01, 1.36567841e+01,
                     1.34149857e+01, 1.28509674e+01, 1.30421829e+01,
                     1.21364517e+01, 1.10667572e+01, 1.34875031e+01,
                     1.29644394e+01, 1.10929565e+01, 1.25928040e+01,
                     1.20705032e+01, 1.37613983e+01, 1.24211426e+01,
                     1.43541031e+01, 1.35802002e+01, 1.35316391e+01,
                     1.51482391e+01, 1.43572388e+01, 1.25008774e+01,
                     1.29395828e+01, 1.34839554e+01, 1.56138916e+01,
                     1.27664642e+01, 1.50478363e+01, 1.47320175e+01,
                     1.60713043e+01, 1.26680298e+01, 1.43690491e+01,
                     1.67355728e+01, 1.33732071e+01, 1.49895935e+01,
                     1.45354385e+01, 1.18312225e+01, 1.37792435e+01,
                     1.63132858e+01, 1.49929428e+01, 1.35270767e+01,
                     1.62972488e+01, 1.49179840e+01, 1.16152649e+01,
                     1.37163849e+01, 1.39367752e+01, 1.43221207e+01,
                     1.31950226e+01, 1.61372986e+01, 1.45505676e+01,
                     1.70516205e+01, 1.59943848e+01, 1.62334900e+01,
                     1.43479309e+01, 1.48412476e+01, 1.56809921e+01,
                     1.69597702e+01, 1.23267288e+01, 1.73257904e+01,
                     1.74552383e+01, 1.61041946e+01, 1.59116135e+01,
                     1.67083588e+01, 1.73401337e+01, 1.30528488e+01,
                     1.82811661e+01, 1.89352417e+01, 1.67904205e+01]])
    psidp = np.ma.array(psidp)
    radar = sample_objects.make_empty_ppi_radar(len(psidp[0]), 1, 1)
    psidp_dict = {
                  'data': psidp,
                  }
    radar.add_field(get_field_name('differential_phase'), psidp_dict)
    # Define real ranges
    radar.range['data'] = 75*np.arange(0, len(psidp[0]))
    return radar


def compare_kdp_estimation_methods():
    # Get profile of noisy psidp
    prof_psidp = _make_real_psidp_radar()
    # Maesaka method
    kdp_mae, phidpf_mae, phidp_mae = kdp_maesaka(prof_psidp, maxiter=1000,
                                                 check_outliers=False)
    # Vulpiani method (note windsize is just a guess here..)
    kdp_vulp, phidp_vulp = kdp_vulpiani(prof_psidp, windsize=30, n_iter=20,
                                        band='X')
    # Kalman filter method
    kdp_schnee, kdp_std_schnee, phidp_schnee = kdp_schneebeli(prof_psidp,
                                                              band='X')
    # Create figure
    plt.figure(figsize=(10, 10))
    plt.subplot(2, 1, 1)
    plt.grid(True)
    plt.title('Kdp estimation')
    plt.hold(True)
    ranges = prof_psidp.range['data']
    plt.plot(ranges, kdp_mae['data'][0])
    plt.plot(ranges, kdp_vulp['data'][0])
    plt.plot(ranges, kdp_schnee['data'][0])
    plt.xlabel('Range [m]')
    plt.ylabel(r'Kdp [deg/km]')
    plt.legend(['Maesaka', 'Vulpiani', 'Schneebeli'], loc=0)
    plt.subplot(2, 1, 2)
    plt.grid(True)
    plt.title('Reconstructed Phidp')
    plt.hold(True)
    ranges = prof_psidp.range['data']
    phidp_mae = 0.5 * (phidp_mae['data'][0] + phidp_mae['data'][0])
    plt.plot(ranges, phidp_mae)
    plt.plot(ranges, phidp_vulp['data'][0])
    plt.plot(ranges, phidp_schnee['data'][0])
    plt.plot(ranges, prof_psidp.fields['differential_phase']['data'][0])
    plt.xlabel('Range [m]')
    plt.ylabel(r'Diff. phase [deg]')
    plt.legend(['Maesaka', 'Vulpiani', 'Schneebeli', 'Real Psidp'], loc=0)
    # Display plot
    plt.show()
# Run the function
compare_kdp_estimation_methods()
